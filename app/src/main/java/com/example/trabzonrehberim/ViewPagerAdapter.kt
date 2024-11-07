package com.example.trabzonrehberim

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: Fragment,private val place: Place) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> HistoryFragment()
            1 -> ActivitiesFragment()
            2 -> LocationFragment()
            else -> HistoryFragment()
        }
        fragment.arguments = Bundle().apply {
            putSerializable("place", place)
        }
        return fragment
    }
}