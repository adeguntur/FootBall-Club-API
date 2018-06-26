package com.ade.kotlin.football.presenter

import com.ade.kotlin.football.api.ApiObject
import com.ade.kotlin.football.model.TeamResponse
import com.ade.kotlin.football.api.API
import com.ade.kotlin.football.view.TeamDetailView
import com.ade.kotlin.football.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by root on 2/3/18.
 */
class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: API,
                          private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()

        async(contextPool.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(ApiObject.getTeamDetail(teamId)),
                        TeamResponse::class.java
                )
            }

            view.showTeamDetail(data.await().teams)
            view.hideLoading()
        }
    }
}