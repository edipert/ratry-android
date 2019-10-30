package com.ratry.android.ui.propose

import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ratry.android.contracts.TCR
import com.ratry.android.data.Candidate
import com.ratry.android.manager.TextileManager
import com.ratry.android.util.toBytes32
import io.textile.pb.Model
import io.textile.textile.Textile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.web3j.protocol.core.DefaultBlockParameterName.EARLIEST
import org.web3j.protocol.core.DefaultBlockParameterName.LATEST
import org.web3j.protocol.core.methods.request.EthFilter


class ProposeViewModel(private val tcr: TCR, private val gson: Gson) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val exception = MutableLiveData<Exception>()
    //12D3KooWE9ZVHVnHKzcd7M6Z9gEwprzSjQ4Ssu58s5uUSjuVKyGG
    private var thread: Model.Thread? = null

    init {
        listenProposeEvent()
    }

    fun getThread(threadId: String) {
        thread = TextileManager.findThreadById(threadId)
    }

    fun addItemInfoToTextile(candidate: Candidate) {
        val jsonData = gson.toJson(candidate).toByteArray()
        val dataString = Base64.encodeToString(jsonData, Base64.DEFAULT)
        TextileManager.addData(dataString, thread?.id, "candidate") { block, exception ->
            block?.let {
                //propose(it.)
            } ?: run {
                this.exception.value = exception
            }
        }
    }

    private fun listenProposeEvent() {
        viewModelScope.launch(Dispatchers.IO) {
            tcr._ApplicationEventFlowable(
                EthFilter(
                    EARLIEST, LATEST,
                    tcr.contractAddress
                )
            ).subscribe({ event ->
                Log.i(
                    "SC_EVENT",
                    "${event.applicant}  ${event.data} ${event.deposit} ${event.listingHash}"
                )
            }, { t ->
                t.printStackTrace()
            })
        }
    }

    private fun propose(hash: String, candidate: Candidate) {
        try {

            val transaction =
                tcr.propose(hash.toBytes32(), candidate.amount, candidate.name).sendAsync().get()

            transaction.apply {
                Log.i(
                    "SC_EVENT", "$blockHash $blockNumber"
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun listFiles() {
        Textile.instance().files.list(thread?.id, "", 10).itemsList.forEach {
            it.filesList.forEach { file ->
                TextileManager.getDataByObject(
                    file.file.hash,
                    object : TypeToken<Candidate>() {}) { candidate ->
                    Log.i("TAG_TEXTILE", candidate.name + " " + candidate.amount)
                    //propose(file.file.hash, place)
                }
            }
        }
    }
}