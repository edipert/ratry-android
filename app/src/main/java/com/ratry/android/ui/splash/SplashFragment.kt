package com.ratry.android.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ratry.android.R
import com.ratry.android.manager.PreferencesManager
import com.ratry.android.ui.base.BaseFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashFragment : BaseFragment() {

    private val preferencesManager: PreferencesManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        GlobalScope.launch {
            delay(1500)
            navigate()
        }
    }

    private fun navigate() {
        if (!preferencesManager.getMnemonic().isNullOrEmpty() || !preferencesManager.getMnemonic().isNullOrEmpty()) {
            navigation.navigateToDirection(SplashFragmentDirections.actionSplashFragmentToMainFragment())
        } else {
            navigation.navigateToDirection(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
        }
    }
}