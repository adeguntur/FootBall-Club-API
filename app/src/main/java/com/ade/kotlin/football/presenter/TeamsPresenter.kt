package com.ade.kotlin.football.presenter

import com.ade.kotlin.football.api.API
import com.ade.kotlin.football.api.ApiObject
import com.ade.kotlin.football.model.TeamResponse
import com.ade.kotlin.football.view.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import com.ade.kotlin.football.util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: API,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(league: String?) {
        view.showLoading()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiObject.getTeams(league)),
                        TeamResponse::class.java
                )
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }
}