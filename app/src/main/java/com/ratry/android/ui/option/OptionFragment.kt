package com.ratry.android.ui.option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ratry.android.R
import com.ratry.android.constant.RestoreOption
import com.ratry.android.manager.PreferencesManager
import com.ratry.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.option_fragment.*
import org.koin.android.ext.android.inject

class OptionFragment : BaseFragment() {

    private val preferencesManager: PreferencesManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.option_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_next_to_key.setOnClickListener {
            routeToRestore(RestoreOption.PRIVATEKEY)
        }

        tv_next_to_seed.setOnClickListener {
            routeToRestore(RestoreOption.SEED)
        }
    }

    private fun routeToRestore(option: RestoreOption) {
        preferencesManager.saveOption(option.value)
        navigation.navigateToDirection(OptionFragmentDirections.actionOptionFragmentToRestoreFragment(option))
    }
}