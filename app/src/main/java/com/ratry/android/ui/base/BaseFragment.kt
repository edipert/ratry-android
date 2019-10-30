package com.ratry.android.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ratry.android.MainActivity

abstract class BaseFragment: Fragment() {

    protected lateinit var navigation: BaseNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
    }

    private fun initNavigation() {
        val activity = activity
        if (activity != null && activity is MainActivity) {
            navigation = (activity as MainActivity?)!!
        }
    }
}