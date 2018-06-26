package com.ade.kotlin.football.view

import com.ade.kotlin.football.model.Team


interface DetailInterface{
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(listDataHome: List<Team>?, listDataAway: List<Team>?)
}