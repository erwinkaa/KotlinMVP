package com.geolstudio.footballmatchschedulemvp.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.androidnetworking.AndroidNetworking
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.R.id.*
import com.geolstudio.footballmatchschedulemvp.favorites.FavoritesFragment
import com.geolstudio.footballmatchschedulemvp.matches.MatchesFragment
import com.geolstudio.footballmatchschedulemvp.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(applicationContext)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                bottom_match -> {
                    loadMatchesFragment(savedInstanceState)
                }
                teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = bottom_match
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame, MatchesFragment(), MatchesFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame,
                    TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame,
                    FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (backPressedOnce) {
            finishAffinity()
        } else {
            backPressedOnce = true
            toast(R.string.pressexitagain)
            Handler().postDelayed({ backPressedOnce = false }, 1000)
        }
    }
}
