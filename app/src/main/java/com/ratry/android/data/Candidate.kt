package com.ratry.android.data

import java.math.BigInteger

data class Candidate(
    val name: String,
    val bannerImgUrl: String?,
    val description: String?,
    val latitude: Double?,
    val longitude: Double?,
    val amount: BigInteger,
    val comment: List<Comment> = emptyList()
)