package com.ratry.android.ui.restore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ratry.android.R
import com.ratry.android.constant.RestoreOption
import com.ratry.android.di.AppModuleCredentials
import com.ratry.android.manager.PreferencesManager
import com.ratry.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.restore_fragment.*
import org.koin.android.ext.android.inject

class RestoreFragment : BaseFragment() {

    private lateinit var restoreOption: RestoreOption

    private val preferencesManager: PreferencesManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.restore_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            RestoreFragmentArgs.fromBundle(it).apply {
                restoreOption = option
                tv_restore_title.text = getString(restoreOption.title)
            }
        }

        edt_restore.setText(if (restoreOption == RestoreOption.SEED) AppModuleCredentials.MNEMONIC else AppModuleCredentials.PRIVATE_KEY)

        btn_done.setOnClickListener {
            if (restoreOption == RestoreOption.SEED)
                preferencesManager.saveMnemonic(edt_restore.text.toString())
            else
                preferencesManager.savePrivateKey(edt_restore.text.toString())

            navigation.navigateToDirection(RestoreFragmentDirections.actionRestoreFragmentToIdentifyFragment())
        }
    }
}