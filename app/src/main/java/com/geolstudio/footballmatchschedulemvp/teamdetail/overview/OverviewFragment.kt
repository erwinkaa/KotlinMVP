package com.geolstudio.footballmatchschedulemvp.teamdetail.overview


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.geolstudio.footballmatchschedulemvp.R

class OverviewFragment : Fragment() {

    private val key = "PASS"
    private lateinit var tvOverview: TextView

    fun newInstance(data: String): OverviewFragment {
        val args = Bundle()
        args.putSerializable(key, data)
        val fragment = OverviewFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_overview, container, false)

        val data = arguments?.getSerializable(key) as String
        tvOverview = rootView.findViewById(R.id.tv_overview)
        tvOverview.text = data

        return rootView
    }


}
