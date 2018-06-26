package com.ade.kotlin.football.view

import com.ade.kotlin.football.model.Teams

/**
 * Created by root on 2/3/18.
 */

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Teams>)
}