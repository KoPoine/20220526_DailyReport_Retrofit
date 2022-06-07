package com.neppplus.a20220526_dailyreport_retrofit.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neppplus.a20220526_dailyreport_retrofit.fragments.ConcentrateFragment
import com.neppplus.a20220526_dailyreport_retrofit.fragments.ConcentrateTodoFragment

class ConViewPagerAdapter(frag : Fragment) : FragmentStateAdapter(frag) {

    override fun getItemCount() : Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ConcentrateFragment()
            else -> ConcentrateTodoFragment()
        }
    }
}
