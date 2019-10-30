package com.ratry.android.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ratry.android.R
import com.ratry.android.ui.wallet.erc.ERCFragment
import com.ratry.android.ui.wallet.eth.ETHFragment
import kotlinx.android.synthetic.main.wallet_fragment.*

class WalletFragment : Fragment() {

    private val titles = listOf("Eth Wallet", "Token")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wallet_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        view_pager.adapter = createViewPagerAdapter()
        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(tabs, view_pager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    private fun createViewPagerAdapter(): RecyclerView.Adapter<*> {
        return object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> ETHFragment()
                    1 -> ERCFragment()
                    else -> Fragment()
                }
            }

            override fun getItemCount(): Int = 2
            override fun containsItem(itemId: Long): Boolean = true
        }
    }
}