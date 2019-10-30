package com.ratry.android.ui.wallet.eth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.utils.Convert
import java.math.BigDecimal

class ETHViewModel(
    private val web3j: Web3j,
    private val credentials: Credentials
) : ViewModel() {

    val balance = MutableLiveData<BigDecimal>()

    fun getBalanceInETH() {
        web3j.ethGetBalance(credentials.address, DefaultBlockParameterName.LATEST).sendAsync().get()
            .apply {
                this@ETHViewModel.balance.postValue(
                    Convert.fromWei(
                        balance.toString(),
                        Convert.Unit.ETHER
                    )
                )
            }
    }
}