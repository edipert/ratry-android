package com.ratry.android.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ratry.android.contracts.IERC20
import com.ratry.android.contracts.TCR
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.utils.Convert
import java.math.BigDecimal
import java.math.BigInteger


class MainViewModel(
    private val web3j: Web3j,
    private val tcr: TCR,
    private val erc20: IERC20
) : ViewModel() {

    val balance = MutableLiveData<BigDecimal>()
    val erc20Balance = MutableLiveData<Uint256>()

    fun getBalanceInETH(address: String) {
        web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get()
            .apply {
                this@MainViewModel.balance.postValue(
                    Convert.fromWei(
                        balance.toString(),
                        Convert.Unit.ETHER
                    )
                )
            }
    }

    fun getErc20TotalSupply() {
        erc20.totalSupply().sendAsync().get().apply {
           erc20Balance.postValue(this)
        }

        tcr.details.sendAsync().get().apply {
            Log.i("DETAIL", this.component1())
        }
    }
}
