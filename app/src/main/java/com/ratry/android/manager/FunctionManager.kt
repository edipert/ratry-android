package com.ratry.android.manager

import com.ratry.android.contracts.TCR
import com.ratry.android.di.AppModuleCredentials
import org.web3j.crypto.Credentials
import org.web3j.crypto.Hash
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction.DEFAULT_GAS
import org.web3j.protocol.core.methods.request.Transaction.createFunctionCallTransaction
import java.math.BigInteger


fun createProposeFunction(tcr: TCR): String {
    return tcr.propose(
        Hash.sha256("sample".toByteArray()),
        BigInteger.valueOf(10),
        "sample"
    ).encodeFunctionCall()
}

@Throws(Exception::class)
fun getNonce(web3j: Web3j, address: String): BigInteger {
    val ethGetTransactionCount =
        web3j.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST)
            .sendAsync()
            .get()

    return ethGetTransactionCount.transactionCount
}

@Throws(Exception::class)
fun estimateGas(
    web3j: Web3j,
    credentials: Credentials,
    encodedFunction: String
): BigInteger {
    val ethEstimateGas = web3j.ethEstimateGas(
        createFunctionCallTransaction(
            credentials.address,
            getNonce(web3j, credentials.address),
            TCR.GAS_PRICE,
            TCR.GAS_LIMIT,
            AppModuleCredentials.TCR_CONTRACT_ADDRESS,
            BigInteger.valueOf(0),
            encodedFunction
        )
    ).send()
    // this was coming back as 50,000,000 which is > the block gas limit of 4,712,388
    // see eth.getBlock("latest")
    return ethEstimateGas.amountUsed.divide(BigInteger.valueOf(100))
}