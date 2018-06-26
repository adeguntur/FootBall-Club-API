package com.ade.kotlin.football.view

import com.ade.kotlin.football.model.Teams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Teams>)
}