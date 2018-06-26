package com.ade.kotlin.football.presenter

import com.ade.kotlin.football.Response
import com.ade.kotlin.football.api.API
import com.ade.kotlin.football.api.ApiObject
import com.ade.kotlin.football.view.DetailInterface
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class Detail(private val anInterface: DetailInterface,
             private val api: API,
             private val gson: Gson){

    fun getBadgeList(teamHome: String?, teamAway: String?){
        anInterface.showLoading()

        async(UI){
            val data = bg {
                gson.fromJson(api
                        .doRequest(ApiObject.getHomeLogo(teamHome)),
                        Response::class.java
                )
            }
            val data2 = bg {
                gson.fromJson(api
                        .doRequest(ApiObject.getAwayLogo(teamAway)),
                        Response::class.java
                )
            }

            anInterface.showTeamsList(data.await().teamId, data2.await().teamId)
            anInterface.hideLoading()
        }
    }
}