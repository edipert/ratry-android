package com.ratry.android.ui.identify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ratry.android.R
import com.ratry.android.ui.base.BaseFragment
import com.ratry.android.util.toast
import io.textile.textile.Textile
import kotlinx.android.synthetic.main.identify_fragment.*

class IdentifyFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.identify_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_done.setOnClickListener {
            if (!edt_name.text.isNullOrEmpty()) {
                Textile.instance().profile.setName(edt_name.text.toString())
            } else {
                context?.toast(getString(R.string.warning_empty_name))
            }

            navigation.navigateToDirection(IdentifyFragmentDirections.actionIdentifyFragmentToMainFragment())
        }
    }
}