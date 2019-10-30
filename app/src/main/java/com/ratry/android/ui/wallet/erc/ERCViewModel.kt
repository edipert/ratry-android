package com.ratry.android.ui.wallet.erc

import androidx.lifecycle.ViewModel
import com.ratry.android.contracts.IERC20
import org.web3j.protocol.Web3j

class ERCViewModel(
    private val web3j: Web3j,
    private val erc: IERC20
) : ViewModel() {
}