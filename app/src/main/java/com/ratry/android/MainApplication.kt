package com.ratry.android

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.ratry.android.di.appModule
import com.ratry.android.di.viewModelModule
import com.ratry.android.manager.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import io.textile.textile.BaseTextileEventListener
import io.textile.textile.Textile
import org.koin.android.ext.android.inject
import java.io.File


class MainApplication : Application() {

    private val preferencesManager: PreferencesManager by inject()

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(appModule, viewModelModule))
        }

        initializeTextile()
    }

    private fun initializeTextile() {
        try {
            val ctx = applicationContext

            val filesDir = ctx.filesDir
            val path = File(filesDir, "textile-repo").absolutePath

            if (!Textile.isInitialized(path)) {
                val phrase = Textile.initializeCreatingNewWalletAndAccount(path, true, false)
                println(phrase)
                // Return phrase to the user for secure, out of app, storage
                preferencesManager.saveTextilePhrase(phrase)
            }

            Textile.launch(ctx, path, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}