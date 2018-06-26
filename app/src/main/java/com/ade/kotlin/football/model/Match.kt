package com.ade.kotlin.football.model

import com.google.gson.annotations.SerializedName

data class Match(
        @SerializedName("idEvent")
        var EventId: String? = null,

        @SerializedName("strHomeTeam")
        var HomeName: String? = null,

        @SerializedName("strAwayTeam")
        var AwayName: String? = null,

        @SerializedName("intHomeScore")
        var HomeScore: String? = null,

        @SerializedName("intAwayScore")
        var AwayScore: String? = null,

        @SerializedName("dateEvent")
        var DateEvent: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var HomeGoalKeeper: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var AwayGoalKeeper: String? = null,

        @SerializedName("strHomeGoalDetails")
        var HomeGoalDetails: String? = null,

        @SerializedName("strAwayGoalDetails")
        var AwayGoalDetails: String? = null,

        @SerializedName("intHomeShots")
        var HomeShots: String? = null,

        @SerializedName("intAwayShots")
        var AwayShots: String? = null,

        @SerializedName("strHomeLineupDefense")
        var HomeDefense: String? = null,

        @SerializedName("strAwayLineupDefense")
        var AwayDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var HomeMidfield: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var AwayMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var HomeForward: String? = null,

        @SerializedName("strAwayLineupForward")
        var AwayForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var HomeSubstitutes: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var AwaySubstitutes: String? = null,

        @SerializedName("strHomeFormation")
        var HomeFormation: String? = null,

        @SerializedName("strAwayFormation")
        var AwayFormation: String? = null,

        @SerializedName("strTeamBadge")
        var TeamBadge: String? = null,

        @SerializedName("idHomeTeam")
        var HomeId: String? = null,

        @SerializedName("idAwayTeam")
        var AwayId: String? = null

)