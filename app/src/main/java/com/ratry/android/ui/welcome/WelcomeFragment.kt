package com.ratry.android.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ratry.android.R
import com.ratry.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.welcome_fragment.*

class WelcomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_import.setOnClickListener {
            navigation.navigateToDirection(WelcomeFragmentDirections.actionWelcomeFragmentToOptionFragment())
        }
    }
}