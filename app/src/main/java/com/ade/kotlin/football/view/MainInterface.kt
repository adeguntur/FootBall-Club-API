package com.ade.kotlin.football.view

import com.ade.kotlin.football.model.Match

interface MainInterface{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(listData: List<Match>?)
}