package com.ade.kotlin.football

import com.ade.kotlin.football.api.API
import org.junit.Test
import org.mockito.Mockito

class ApiTest {
    @Test
    fun testDoRequest() {
        val api = Mockito.mock(API::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=Spanish%20La%20Liga"
        api.doRequest(url)
        Mockito.verify(api).doRequest(url)
    }
}