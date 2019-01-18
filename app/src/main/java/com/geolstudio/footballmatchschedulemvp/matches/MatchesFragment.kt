package com.geolstudio.footballmatchschedulemvp.matches


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager

import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.R.id.m_search
import com.geolstudio.footballmatchschedulemvp.R.menu.menu_search_match
import com.geolstudio.footballmatchschedulemvp.matches.childmatches.MatchFragment
import com.geolstudio.footballmatchschedulemvp.util.ViewPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import com.geolstudio.footballmatchschedulemvp.matches.searchmatches.SearchMatchActivity
import org.jetbrains.anko.support.v4.startActivity


class MatchesFragment : Fragment() {

    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_matches, container, false)

        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.matches)
        setHasOptionsMenu(true)

        viewPager = rootView.findViewById(R.id.vp_matches)
        tabLayout = rootView.findViewById(R.id.tablayout)
        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(MatchFragment().newInstance("next"))
        adapter.addFragment(MatchFragment().newInstance("last"))
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(menu_search_match, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            m_search -> {
                startActivity<SearchMatchActivity>()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
