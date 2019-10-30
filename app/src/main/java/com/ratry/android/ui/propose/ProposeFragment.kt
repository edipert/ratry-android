package com.ratry.android.ui.propose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ratry.android.R
import com.ratry.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.propose_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProposeFragment : BaseFragment() {

    private val viewModel: ProposeViewModel by viewModel()

    private var threadId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.propose_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setClickListeners()

        arguments?.let {
            ProposeFragmentArgs.fromBundle(it).apply {
                viewModel.getThread(threadId)
            }
        }
    }

    private fun setClickListeners() {
        btn_propose.setOnClickListener {
            /*viewModel.propose(
                Candidate(
                    edt_item_name.text.toString(),
                    BigInteger.valueOf(edt_amount.text.toString().toLong())
                )
            )*/

            viewModel.listFiles()
        }
    }
}