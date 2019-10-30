package com.ratry.android.constant

import com.ratry.android.R

enum class RestoreOption(val title: Int, val value: String) {
    SEED(R.string.label_restore_from_seed, "mnemonic"),
    PRIVATEKEY(R.string.label_restore_from_key, "privateKey")
}