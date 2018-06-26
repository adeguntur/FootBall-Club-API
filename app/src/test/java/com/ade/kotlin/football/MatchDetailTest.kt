package com.ade.kotlin.football

import com.ade.kotlin.football.api.API
import com.ade.kotlin.football.api.ApiObject
import com.ade.kotlin.football.model.Match
import com.ade.kotlin.football.presenter.MatchDetail
import com.ade.kotlin.football.view.MainInterface
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailTest {
    @Test
    fun testDetailMatch() {
        val Team: MutableList<Match> = mutableListOf()
        val Response = Response(Team)
        val League = "Spanish La Liga"

        Mockito.`when`(gson.fromJson(api
                .doRequest(ApiObject.getDetailMatch(League)),
                Response::class.java
        )).thenReturn(Response)

        matchDetail.getDetailMatch(League)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchList(Team)
        Mockito.verify(view).hideLoading()
    }

    @Mock
    private lateinit var view: MainInterface
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var api: API
    @Mock
    private lateinit var matchDetail: MatchDetail

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        matchDetail = MatchDetail(view, api, gson, TCProvider())
    }
}