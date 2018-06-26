package com.ade.kotlin.football

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ade.kotlin.football.R.id.*
import com.ade.kotlin.football.R.layout.activity_home
import com.ade.kotlin.football.fragment.FavoriteMatchFragment
import com.ade.kotlin.football.fragment.MatchFragment
import com.ade.kotlin.football.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener({ item ->
            when (item.itemId) {
                past -> {
                    past
                    loadPastMatchFragment(savedInstanceState)
                }

                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
                teams -> {
                    R.id.teams
                    loadNextMatchFragment(savedInstanceState)
                }

            }
            true
        })
        bottom_navigation.selectedItemId = past
    }


    private fun loadPastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.simpleName)
                    .commit()
        }
    }
}
