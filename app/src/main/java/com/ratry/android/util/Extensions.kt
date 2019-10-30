package com.ratry.android.util

import android.content.Context
import android.widget.Toast


fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun String.toBytes32(): ByteArray =
    this.toByteArray().copyOfRange(0, (if (this.length >= 33) 32 else this.length))

fun hexStringToByteArray(s: String): ByteArray {
    val len = s.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
        i += 2
    }
    return data
}