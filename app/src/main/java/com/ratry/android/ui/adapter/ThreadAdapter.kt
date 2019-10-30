package com.ratry.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ratry.android.R
import io.textile.pb.Model
import kotlinx.android.synthetic.main.thread_item.view.*

class ThreadAdapter : RecyclerView.Adapter<ThreadAdapter.ThreadViewHolder>() {

    private val dataList = mutableListOf<Model.Thread>()

    private var listener: OnThreadClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.thread_item, parent, false)
        return ThreadViewHolder(view)
    }

    fun setDataList(dataList: MutableList<Model.Thread>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ThreadViewHolder, position: Int) = holder.bind(listener, position)

    fun setOnThreadClickListener(listener: OnThreadClickListener) {
        this.listener = listener
    }

    inner class ThreadViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(listener: OnThreadClickListener?, position: Int) {
            val thread = dataList[position]
            view.tv_thread_name.text = thread.name

            view.setOnClickListener {
                listener?.onThreadClick(thread)
            }
        }
    }

    interface OnThreadClickListener {
        fun onThreadClick(thread: Model.Thread)
    }
}