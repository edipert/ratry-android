package com.ratry.android.di

import com.google.gson.Gson
import com.ratry.android.contracts.IERC20
import com.ratry.android.contracts.TCR
import com.ratry.android.manager.PreferencesManager
import com.ratry.android.ui.list.ListViewModel
import com.ratry.android.ui.main.MainViewModel
import com.ratry.android.ui.propose.ProposeViewModel
import com.ratry.android.ui.restore.RestoreViewModel
import com.ratry.android.ui.wallet.erc.ERCViewModel
import com.ratry.android.ui.wallet.eth.ETHViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger


object AppModuleCredentials {
    const val INFURA_URL = "wss://rinkeby.infura.io/ws/v3/"
    const val INFURA_KEY = "edeb13a12cd944608fe608abbb1219d3"

    const val PRIVATE_KEY = "0xEAF7D4EF85174F28661D099D9808D644AF87BCF4D6222EA820FC8ABEB8FCF7BB"
    const val TCR_CONTRACT_ADDRESS = "0x7eD1406c03a10EA535186833F332348e4d47f4d0"
    const val ERC20_CONTRACT_ADDRESS = "0x91195652c23A7dC6082bc37c138ddf8463701046"

    val GAS_LIMIT = BigInteger.valueOf(4_000_000)
    val GAS_PRICE = BigInteger.valueOf(5_000_000_000L)

    const val MNEMONIC =
        "firm tank fatal avocado sleep diary bench kitchen tissue broom color always"
}

val appModule = module {
    single<Web3j> {
        val web3jService = WebSocketService(
            "${AppModuleCredentials.INFURA_URL}${AppModuleCredentials.INFURA_KEY}",
            true
        )
        web3jService.connect()
        Web3j.build(web3jService)
        //Web3j.build(HttpService("${AppModuleCredentials.INFURA_URL}${AppModuleCredentials.INFURA_KEY}"))
    }

    single { (credentials: Credentials) ->
        TCR.load(
            AppModuleCredentials.TCR_CONTRACT_ADDRESS,
            get(),
            credentials,
            object : ContractGasProvider {
                override fun getGasLimit(contractFunc: String?): BigInteger {
                    /*val encodedFunction = when (contractFunc) {
                        TCR.FUNC_PROPOSE -> createProposeFunction(get())
                        else -> throw TransactionException("")
                    }*/

                    return AppModuleCredentials.GAS_LIMIT
                }

                override fun getGasLimit(): BigInteger {
                    return AppModuleCredentials.GAS_LIMIT
                }

                override fun getGasPrice(contractFunc: String?): BigInteger {
                    return get<Web3j>().ethGasPrice().send().gasPrice
                }

                override fun getGasPrice(): BigInteger {
                    return get<Web3j>().ethGasPrice().send().gasPrice
                }
            })
    }

    single { (credentials: Credentials) ->
        IERC20.load(
            AppModuleCredentials.ERC20_CONTRACT_ADDRESS,
            get(),
            credentials,
            object : ContractGasProvider {
                override fun getGasLimit(contractFunc: String?): BigInteger {
                    return AppModuleCredentials.GAS_LIMIT
                }

                override fun getGasLimit(): BigInteger {
                    return AppModuleCredentials.GAS_LIMIT
                }

                override fun getGasPrice(contractFunc: String?): BigInteger {
                    return get<Web3j>().ethGasPrice().send().gasPrice
                }

                override fun getGasPrice(): BigInteger {
                    return get<Web3j>().ethGasPrice().send().gasPrice
                }
            })
    }

    single(named("mnemonic")) { (mnemonic: String, password: String) ->
        val masterKeypair =
            Bip32ECKeyPair.generateKeyPair(
                MnemonicUtils.generateSeed(
                    mnemonic,
                    password
                )
            )
        val path = intArrayOf(
            44 or Bip32ECKeyPair.HARDENED_BIT,
            60 or Bip32ECKeyPair.HARDENED_BIT,
            0 or Bip32ECKeyPair.HARDENED_BIT,
            0,
            0
        )
        val driveKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path)

        Credentials.create(driveKeyPair)
    }

    single(named("privateKey")) { (privateKey: String) ->
        Credentials.create(privateKey)
    }

    single {
        PreferencesManager(androidApplication())
    }

    single {
        Gson()
    }
}

val viewModelModule = module {
    viewModel { (tcr: TCR, erc: IERC20) ->
        MainViewModel(get(), tcr, erc)
    }

    viewModel {
        ETHViewModel(get(), get(named(get<PreferencesManager>().getOption())))
    }

    viewModel {
        RestoreViewModel()
    }

    viewModel {
        ListViewModel()
    }

    viewModel {
        ERCViewModel(get(), get())
    }

    viewModel {
        ProposeViewModel(get(), get())
    }
}

