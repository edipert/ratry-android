package com.ratry.android.ui.wallet.eth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ratry.android.R
import com.ratry.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.eth_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ETHFragment : BaseFragment() {

    private val viewModel: ETHViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.eth_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.balance.observe(viewLifecycleOwner, Observer {
            tv_balance.text = "$it ETH"
        })

        viewModel.getBalanceInETH()
    }
}