package com.ratry.android.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ratry.android.R
import com.ratry.android.ui.adapter.ListAdapter
import com.ratry.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : BaseFragment() {

    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        observe()
        extractArguments()
    }

    private fun initViews() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rv_list.layoutManager = staggeredGridLayoutManager
        rv_list.setHasFixedSize(true)
    }

    private fun observe() {
        viewModel.listings.observe(viewLifecycleOwner, Observer {
            rv_list.adapter = ListAdapter(it)
        })
    }

    private fun extractArguments() {
        arguments?.let {
            ListFragmentArgs.fromBundle(it).apply {
                txt_title.text = title
                viewModel.listFiles(threadId)
            }
        }
    }
}