package com.ratry.android.ui.wallet.erc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ratry.android.R
import com.ratry.android.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class ERCFragment : BaseFragment() {

    private val viewModel: ERCViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.erc_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}