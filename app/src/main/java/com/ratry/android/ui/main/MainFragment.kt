package com.ratry.android.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ratry.android.R
import com.ratry.android.constant.RestoreOption
import com.ratry.android.contracts.IERC20
import com.ratry.android.contracts.TCR
import com.ratry.android.manager.PreferencesManager
import com.ratry.android.manager.TextileManager
import com.ratry.android.ui.adapter.ThreadAdapter
import com.ratry.android.ui.base.BaseFragment
import com.ratry.android.util.toast
import io.textile.pb.Model
import io.textile.textile.Textile
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.web3j.crypto.Credentials
import java.math.BigInteger
import java.text.DecimalFormat
import kotlin.math.pow


class MainFragment : BaseFragment(), ThreadAdapter.OnThreadClickListener {

    private val preferencesManager: PreferencesManager by inject()

    private val credentials: Credentials by inject(named(preferencesManager.getOption())) {
        when (preferencesManager.getOption()) {
            RestoreOption.SEED.value -> parametersOf(preferencesManager.getMnemonic(), "")
            RestoreOption.PRIVATEKEY.value -> parametersOf(preferencesManager.getPrivateKey())
            else -> parametersOf()
        }
    }

    private val tcr: TCR by inject {
        parametersOf(credentials)
    }

    private val erc: IERC20 by inject {
        parametersOf(credentials)
    }

    private val viewModel: MainViewModel by viewModel {
        parametersOf(tcr, erc)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
        setClickListeners()
        initViews()
    }

    private fun initViews() {
        tv_profile_name.text = getString(R.string.label_welcome_title, Textile.instance().profile.name().split(" ")[0])
        tv_public_address.text = credentials.address

        rv_thread_list.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_thread_list.setHasFixedSize(true)
        rv_thread_list.adapter = ThreadAdapter().apply {
            setDataList(TextileManager.getThreadList().itemsList)
            setOnThreadClickListener(this@MainFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        viewModel.getBalanceInETH(credentials.address)
        viewModel.getErc20TotalSupply()

        viewModel.balance.observe(this, Observer {
            tv_balance.text = "$it ETH"
        })

        viewModel.erc20Balance.observe(this, Observer {
            val pattern = "###,###.###"
            val decimalFormat = DecimalFormat(pattern)
            tv_erc_balance.text = "${decimalFormat.format(
                (it.value / BigInteger.valueOf(
                    10.0.pow(18.0).toLong()
                ))
            )} OPN"
        })
    }

    private fun setClickListeners() {
        btn_fab.setOnClickListener {

        }

        iv_wallet.setOnClickListener {
            navigation.navigateToDirection(
                MainFragmentDirections.actionMainFragmentToWalletFragment()
            )
        }
    }

    override fun onThreadClick(thread: Model.Thread) {
        navigation.navigateToDirection(
            MainFragmentDirections.actionMainFragmentToListFragment(
                thread.name,
                thread.id
            )
        )
    }

    private fun toast(message: String) {
        this@MainFragment.context?.toast(message)
    }
}
