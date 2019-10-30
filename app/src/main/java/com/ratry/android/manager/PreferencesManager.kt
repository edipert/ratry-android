package com.ratry.android.manager

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class PreferencesManager constructor(application: Application) {

    var preferences: SharedPreferences =
        application.baseContext.getSharedPreferences("ratry_preferences", Context.MODE_PRIVATE)

    companion object {
        const val MNEMONIC = "MNEMONIC"
        const val PRIVATE_KEY = "PRIVATE_KEY"
        const val OPTION = "OPTION"
        const val TEXTILE_PHRASE = "TEXTILE_PHRASE"
    }

    fun getTextilePhrase() = preferences.getString(TEXTILE_PHRASE, null)

    fun saveTextilePhrase(value: String) {
        val edit = preferences.edit()
        edit.putString(TEXTILE_PHRASE, value)
        edit.apply()
    }

    fun getOption(): String = preferences.getString(OPTION, null) ?: ""

    fun saveOption(value: String) {
        val edit = preferences.edit()
        edit.putString(OPTION, value)
        edit.apply()
    }

    fun getMnemonic() = preferences.getString(MNEMONIC, null)

    fun saveMnemonic(value: String) {
        val edit = preferences.edit()
        edit.putString(MNEMONIC, value)
        edit.apply()
    }

    fun getPrivateKey() = preferences.getString(PRIVATE_KEY, null)

    fun savePrivateKey(value: String) {
        val edit = preferences.edit()
        edit.putString(PRIVATE_KEY, value)
        edit.apply()
    }
}