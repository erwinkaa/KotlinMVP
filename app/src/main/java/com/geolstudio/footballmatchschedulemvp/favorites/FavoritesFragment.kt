package com.geolstudio.footballmatchschedulemvp.favorites


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.favorites.favoritematch.FavoriteMatchFragment
import com.geolstudio.footballmatchschedulemvp.favorites.favoriteteams.FavoriteTeamFragment
import com.geolstudio.footballmatchschedulemvp.util.ViewPagerAdapter

class FavoritesFragment : Fragment() {

    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorites, container, false)

        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.favorites)

        viewPager = rootView.findViewById(R.id.vp_favorites)
        tabLayout = rootView.findViewById(R.id.tablayout)
        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavoriteMatchFragment())
        adapter.addFragment(FavoriteTeamFragment())
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

        return rootView
    }


}
