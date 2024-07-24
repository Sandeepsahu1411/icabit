package com.example.icabit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.icabit.fragments.TodayJobsFragment
import com.example.icabit.fragments.YesterdayJobFragment

class JobTabAdapter(fragmentActivity: FragmentActivity, private val totalTabs: Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TodayJobsFragment()
            1 -> YesterdayJobFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}