package com.common.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabFragmentAdapter(fm: FragmentManager, private val mFragments: List<Fragment>, private val mTitles: MutableList<String>?) : FragmentStatePagerAdapter(fm) {

    fun updateTabTitle(titles: List<String>) {
        if (mTitles != null) {
            if (!mTitles.isEmpty()) {
                mTitles.clear()
            }
            mTitles.addAll(titles)
            notifyDataSetChanged()
        }
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles!![position]
    }
}