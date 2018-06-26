package com.ade.kotlin.football.api

import java.net.URL


class API{
    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}