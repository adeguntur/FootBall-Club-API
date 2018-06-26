package com.ade.kotlin.football

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.ade.kotlin.football.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)


    @Test
    fun aplikasiTest() {
        Thread.sleep(2000)

        //buka fragment Past Match
        Espresso.onView(ViewMatchers.withId(past)).perform(ViewActions.click())

        Thread.sleep(2000)

        // mencari data dengan value text Barcelona
        Espresso.onView(ViewMatchers.withText("Barcelona"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // jika data ditemukan akan melakukan Action Click
        Espresso.onView(ViewMatchers.withText("Barcelona")).perform(ViewActions.click())

        Thread.sleep(2000)
        //menampilkan detail data text value Barcelona
        Espresso.onView(ViewMatchers.withText("Barcelona")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check apakah ada tombol tambah ke favorite?
        Espresso.onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // jika ada maka akan menambahkan ke favorite
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Thread.sleep(2000)

        //check lagi apakah ada tombol tambah ke favorite?
        Espresso.onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // jika ada maka akan menambahkan ke favorite untuk kedua kali maka akan menjadi remove dari favorite
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Thread.sleep(1000)
        Espresso.pressBack()

        //buka fragment Next Match
        Espresso.onView(ViewMatchers.withId(next)).perform(ViewActions.click())

        //dan kembali lagi buka fragment Past Match
        Espresso.onView(ViewMatchers.withId(past)).perform(ViewActions.click())

    }

}