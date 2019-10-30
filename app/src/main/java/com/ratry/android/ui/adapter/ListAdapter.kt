package com.ratry.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ratry.android.R
import com.ratry.android.data.Candidate
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(
    private val dataList: List<Candidate>
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.bind(position)

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            dataList[position].apply {
                itemView.iv_list_item.setImageURI("https://www.vailhealth.org/files/182/7/747/JacksPlaceSummer17-6131-1_Lo-res_reg.jpg")
                itemView.txt_name.text = name
            }
        }
    }
}