package com.ade.kotlin.football.model

data class Favorite(val id: Long?,
                    val HomeId: String?,
                    val AwayId: String?,
                    val eventDate: String?,
                    val HomeName: String?,
                    val AwayName: String?,
                    val HomeScore: String?,
                    val AwayScore: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_NAME: String = "HOME_NAME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"

    }
}