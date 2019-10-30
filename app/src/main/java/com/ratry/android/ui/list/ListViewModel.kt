package com.ratry.android.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.reflect.TypeToken
import com.ratry.android.data.Candidate
import com.ratry.android.manager.TextileManager
import io.textile.textile.Textile

class ListViewModel : ViewModel() {

    val listings = MutableLiveData<List<Candidate>>()

    fun listFiles(threadId: String) {
        val list = mutableListOf<Candidate>()
        Textile.instance().files.list(threadId, "", 10).itemsList.forEach {
            it.filesList.forEach { file ->
                TextileManager.getDataByObject(
                    file.file.hash,
                    object : TypeToken<Candidate>() {}) { candidate ->
                    list.add(candidate)
                }
            }
        }

        listings.postValue(list)
    }
}