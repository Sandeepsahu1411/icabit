package com.example.icabit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.icabit.fragments.CompanyFragment
import com.example.icabit.fragments.DriverFragment

class CreatAcTabAdapter(fragmentActivity: FragmentActivity, private val totalTabs: Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CompanyFragment()
            1 -> DriverFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}