package com.example.openinapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.openinapp.Fragments.RecentLinkFragment
import com.example.openinapp.Fragments.TopLinkFragment

class PagerFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
         return 2;
    }

    override fun getItem(position: Int): Fragment {
             if(position==0)
                 return RecentLinkFragment()
             else
                 return TopLinkFragment()
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "Recent Links" else "Top links"
    }
}