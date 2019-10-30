package com.ratry.android.manager

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.protobuf.Descriptors
import com.ratry.android.BuildConfig
import io.textile.pb.Model
import io.textile.pb.View
import io.textile.textile.Contacts
import io.textile.textile.Handlers
import io.textile.textile.Textile

object TextileManager {
    fun addData(
        dataString: String,
        threadId: String?,
        caption: String,
        callback: (Model.Block?, Exception?) -> Unit
    ) {
        Textile.instance().files.addData(
            dataString,
            threadId,
            caption,
            object : Handlers.BlockHandler {
                override fun onComplete(block: Model.Block?) {
                    callback(block, null)
                }

                override fun onError(e: Exception?) {
                    e?.printStackTrace()
                    callback(null, e)
                }
            })
    }

    fun createNewThread(name: String, callback: (Model.Thread) -> Unit) {
        val schema = View.AddThreadConfig.Schema.newBuilder()
            .setPreset(View.AddThreadConfig.Schema.Preset.BLOB)
            .build()

        val config = View.AddThreadConfig.newBuilder()
            .setKey(BuildConfig.VERSION_NAME)
            .setName(name)
            .setType(Model.Thread.Type.OPEN)
            .setSharing(Model.Thread.Sharing.SHARED)
            .setSchema(schema)
            .build()

        callback(Textile.instance().threads.add(config))
    }

    fun <T> getDataByObject(hash: String, responseType: TypeToken<T>, callback: (T) -> Unit) {
        Textile.instance().files.content(
            hash/*"QmWizvC3oGmcHCS4cku7Cxx6GwqUPSNNw8JMonEPm7vtLB"*/,
            object : Handlers.DataHandler {
                override fun onComplete(data: ByteArray?, media: String?) {
                    data?.let {
                        callback(Gson().fromJson<T>(String(it, Charsets.UTF_8), responseType.type))
                    }
                }

                override fun onError(e: Exception?) {
                    e?.printStackTrace()
                }
            })
    }

    fun findThreadById(threadId: String): Model.Thread = Textile.instance().threads.get(threadId)

    fun getThreadList(): Model.ThreadList = Textile.instance().threads.list()

    fun addContact() {
        Textile.instance().contacts.add(Model.Contact.getDefaultInstance())
    }
}