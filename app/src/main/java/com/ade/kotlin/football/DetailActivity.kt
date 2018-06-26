package com.ade.kotlin.football

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.ade.kotlin.football.R.drawable.ic_add_to_favorites
import com.ade.kotlin.football.R.drawable.ic_added_to_favorites
import com.ade.kotlin.football.R.id.add_to_favorite
import com.ade.kotlin.football.R.menu.detail_menu
import com.ade.kotlin.football.R.string.*
import com.ade.kotlin.football.api.API
import com.ade.kotlin.football.db.database
import com.ade.kotlin.football.key.KEY
import com.ade.kotlin.football.model.Favorite
import com.ade.kotlin.football.model.Match
import com.ade.kotlin.football.model.Team
import com.ade.kotlin.football.presenter.Detail
import com.ade.kotlin.football.presenter.MatchDetail
import com.ade.kotlin.football.view.DetailInterface
import com.ade.kotlin.football.view.MainInterface
import com.bumptech.glide.Glide
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity(), DetailInterface, MainInterface {

    private lateinit var HomeId: String
    private lateinit var AwayId: String
    private lateinit var EventId: String

    private lateinit var HomeName: String
    private lateinit var AwayName: String
    private lateinit var HomeScore: String
    private lateinit var AwayScore: String

    private lateinit var MatchDate: TextView

    private lateinit var ProgressBar: ProgressBar

    private lateinit var DetailPresenter: Detail
    private lateinit var DetailMatchPresenter: MatchDetail

    private lateinit var TeamHome: Team
    private lateinit var TeamAway: Team
    private lateinit var Match: Match

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var NameAway: TextView
    private lateinit var ScoreAway: TextView
    private lateinit var FormationAway: TextView
    private lateinit var GoalsAway: TextView
    private lateinit var ShotsAway: TextView
    private lateinit var GoalkeeperAway: TextView
    private lateinit var DefenseAway: TextView
    private lateinit var ForwardAway: TextView
    private lateinit var SubtitutesAway: TextView
    private lateinit var MidfieldAway: TextView
    private lateinit var ImgAway: ImageView

    private lateinit var NameHome: TextView
    private lateinit var ScoreHome: TextView
    private lateinit var FormationHome: TextView
    private lateinit var GoalsHome: TextView
    private lateinit var ShotsHome: TextView
    private lateinit var GoalkeeperHome: TextView
    private lateinit var DefenseHome: TextView
    private lateinit var ForwardHome: TextView
    private lateinit var SubtitutesHome: TextView
    private lateinit var MidfieldHome: TextView
    private lateinit var ImgHome: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        scrollView {

            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(16)

                ProgressBar = progressBar {

                }.lparams {
                    gravity = Gravity.CENTER
                }

                linearLayout {
                    orientation = LinearLayout.VERTICAL

                    MatchDate = textView {
                        id = R.id.MatchDate
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        gravity = Gravity.CENTER
                    }

                    linearLayout {
                        padding = dip(16)
                        orientation = LinearLayout.HORIZONTAL
                        ScoreHome = textView {
                            id = R.id.ScoreMatchHome
                            textSize = 20f
                            gravity = Gravity.CENTER
                        }.lparams {
                            weight = 1f
                            rightMargin = 16
                        }

                        textView {
                            text = getString(vs)
                            gravity = Gravity.CENTER
                        }.lparams {
                            weight = 1f
                        }
                        ScoreAway = textView {
                            id = R.id.ScoreMatchAway
                            textSize = 22f
                            gravity = Gravity.CENTER
                        }.lparams {
                            weight = 1f
                            leftMargin = 16
                        }
                    }.lparams {
                        gravity = Gravity.CENTER
                    }


                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            ImgHome = imageView {
                                id = R.id.ImgHome
                            }.lparams {
                                width = dip(150)
                                height = dip(150)
                            }

                            NameHome = textView {
                                id = R.id.NameHome
                                textSize = 18f
                            }.lparams {
                                gravity = Gravity.CENTER
                            }

                            FormationHome = textView {
                                id = R.id.FormationHome
                            }.lparams {
                                gravity = Gravity.CENTER
                            }


                        }.lparams {
                            weight = 1f
                        }


                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            ImgAway = imageView {
                                id = R.id.ImgAway
                            }.lparams {
                                width = dip(150)
                                height = dip(150)
                            }
                            NameAway = textView {
                                id = R.id.NameAway
                                textSize = 18f
                            }.lparams {
                                gravity = Gravity.CENTER
                            }

                            FormationAway = textView {
                                id = R.id.FormationAway
                            }.lparams {
                                gravity = Gravity.CENTER
                            }
                        }.lparams {
                            weight = 1f
                        }
                    }

                    //goals

                    textView {
                        text = getString(Goals)
                    }.lparams {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }


                    textView {
                        text = getString(Home)

                    }.lparams {
                        weight = 1f
                    }

                    GoalsHome = textView {
                        id = R.id.GoalsHome
                        textSize = 15f
                    }.lparams {
                        weight = 1f
                    }


                    textView {
                        text = getString(Away)

                    }.lparams {
                        weight = 1f
                    }

                    GoalsAway = textView {
                        id = R.id.GoalsAway
                        textSize = 15f
                    }.lparams {
                        weight = 1f
                        bottomMargin = dip(16)
                    }

                    //shots

                    textView {
                        text = getString(Shots)
                    }.lparams {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }

                    textView {
                        text = getString(Home)

                    }.lparams {
                        weight = 1f
                    }

                    ShotsHome = textView {
                        id = R.id.ShotsHome
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                    }

                    textView {
                        text = getString(Away)

                    }.lparams {
                        weight = 1f
                    }

                    ShotsAway = textView {
                        id = R.id.ShotsAway
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                        bottomMargin = dip(16)
                    }


                    //lineup

                    textView {
                        text = getString(Lineup)
                    }.lparams {
                        bottomMargin = dip(16)
                        gravity = Gravity.CENTER
                        bottomMargin = dip(25)

                    }

                    //goalkeeper

                    textView {
                        text = getString(Goalkeeper)

                    }.lparams {
                        weight = 1f
                        gravity = Gravity.CENTER

                    }
                    textView {
                        text = getString(Home)

                    }.lparams {
                        weight = 1f
                    }
                    GoalkeeperHome = textView {
                        id = R.id.GoalkeeperHome

                        textSize = 18f
                    }.lparams {
                        weight = 1f
                        rightMargin = dip(16)
                    }

                    textView {
                        text = getString(Away)
                    }.lparams {
                        weight = 1f
                    }

                    GoalkeeperAway = textView {
                        id = R.id.GoalkeeperAway
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                        bottomMargin = dip(16)
                    }

                    //defense

                    textView {
                        text = getString(Defense)
                    }.lparams {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }

                    textView {
                        text = getString(Home)

                    }.lparams {
                        weight = 1f
                    }

                    DefenseHome = textView {
                        id = R.id.DefenseHome
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                    }

                    textView {
                        text = getString(Away)

                    }.lparams {
                        weight = 1f
                    }
                    DefenseAway = textView {
                        id = R.id.DefenseAway
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                        bottomMargin = dip(16)
                    }


                    //midfield

                    textView {
                        text = getString(Midfield)
                    }.lparams {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }

                    textView {
                        text = getString(Home)

                    }.lparams {
                        weight = 1f
                    }

                    MidfieldHome = textView {
                        id = R.id.MidfieldHome
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                    }

                    textView {
                        text = getString(Away)

                    }.lparams {
                        weight = 1f
                    }

                    MidfieldAway = textView {
                        id = R.id.MidfieldAway
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                        bottomMargin = dip(16)
                    }


                    //Forward

                    textView {
                        text = getString(Forward)
                    }.lparams {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }

                    textView {
                        text = getString(Home)

                    }.lparams {
                        weight = 1f
                    }

                    ForwardHome = textView {
                        id = R.id.ForwardHome

                        textSize = 18f
                    }.lparams {
                        weight = 1f
                    }

                    textView {
                        text = getString(Away)

                    }.lparams {
                        weight = 1f
                    }

                    ForwardAway = textView {
                        id = R.id.ForwardAway
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                        bottomMargin = dip(16)
                    }


                    //Subtitutes

                    textView {
                        text = getString(Subtitute)
                    }.lparams {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }

                    textView {
                        text = getString(Home)

                    }.lparams {
                        weight = 1f
                    }

                    SubtitutesHome = textView {
                        id = R.id.SubstitutesHome
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                    }

                    textView {
                        text = getString(Away)

                    }.lparams {
                        weight = 1f
                    }

                    SubtitutesAway = textView {
                        id = R.id.SubstitutesAway
                        textSize = 18f
                    }.lparams {
                        weight = 1f
                        bottomMargin = dip(16)
                    }

                }
            }
        }

        val i = intent

        HomeId = i.getStringExtra(KEY.HOME_ID)
        AwayId = i.getStringExtra(KEY.AWAY_ID)
        EventId = i.getStringExtra(KEY.EVENT_ID)

        favoriteState()
        val request = API()
        val gson = Gson()

        DetailPresenter = Detail(this, request, gson)
        DetailMatchPresenter = MatchDetail(this, request, gson)

        DetailPresenter.getBadgeList(HomeId, AwayId)
        DetailMatchPresenter.getDetailMatch(EventId)
    }

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    private fun View.invisible() {
        visibility = View.GONE
    }

    override fun showLoading() {
        ProgressBar.visible()
    }

    override fun hideLoading() {
        ProgressBar.invisible()
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(HOME_ID = {id}) and (AWAY_ID = {id2}) and (EVENT_DATE = {id3})",
                            "id" to HomeId,
                            "id2" to AwayId,
                            "id3" to EventId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite()
                else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.HOME_ID to HomeId,
                        Favorite.AWAY_ID to AwayId,
                        Favorite.EVENT_DATE to EventId,
                        Favorite.HOME_NAME to HomeName,
                        Favorite.AWAY_NAME to AwayName,
                        Favorite.HOME_SCORE to HomeScore,
                        Favorite.AWAY_SCORE to AwayScore)
            }

            toast("Ditambahkan pada favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE,
                        "(HOME_ID = {id}) and (AWAY_ID = {id2}) and (EVENT_DATE = {id3})",
                        "id" to HomeId,
                        "id2" to AwayId,
                        "id3" to EventId)
            }
            toast("Dihapus dari favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    override fun showMatchList(listData: List<Match>?) {
        Match = Match(listData?.get(0)?.EventId,
                listData?.get(0)?.HomeName,
                listData?.get(0)?.AwayName,
                listData?.get(0)?.HomeScore,
                listData?.get(0)?.AwayScore,
                listData?.get(0)?.DateEvent,
                listData?.get(0)?.HomeGoalKeeper,
                listData?.get(0)?.AwayGoalKeeper,
                listData?.get(0)?.HomeGoalDetails,
                listData?.get(0)?.AwayGoalDetails,
                listData?.get(0)?.HomeShots,
                listData?.get(0)?.AwayShots,
                listData?.get(0)?.HomeDefense,
                listData?.get(0)?.AwayDefense,
                listData?.get(0)?.HomeMidfield,
                listData?.get(0)?.AwayMidfield,
                listData?.get(0)?.HomeForward,
                listData?.get(0)?.AwayForward,
                listData?.get(0)?.HomeSubstitutes,
                listData?.get(0)?.AwaySubstitutes,
                listData?.get(0)?.HomeFormation,
                listData?.get(0)?.AwayFormation,
                listData?.get(0)?.TeamBadge,
                listData?.get(0)?.HomeId,
                listData?.get(0)?.AwayId)

        NameAway.text = listData?.get(0)?.AwayName
        ScoreAway.text = listData?.get(0)?.AwayScore
        FormationAway.text = listData?.get(0)?.AwayFormation
        GoalsAway.text = listData?.get(0)?.AwayGoalDetails
        GoalkeeperAway.text = listData?.get(0)?.AwayGoalKeeper
        ShotsAway.text = listData?.get(0)?.AwayShots
        DefenseAway.text = listData?.get(0)?.AwayDefense
        ForwardAway.text = listData?.get(0)?.AwayForward
        SubtitutesAway.text = listData?.get(0)?.AwaySubstitutes
        MidfieldAway.text = listData?.get(0)?.AwayMidfield
        NameHome.text = listData?.get(0)?.HomeName
        ScoreHome.text = listData?.get(0)?.HomeScore
        FormationHome.text = listData?.get(0)?.HomeFormation
        GoalsHome.text = listData?.get(0)?.HomeGoalDetails
        GoalkeeperHome.text = listData?.get(0)?.HomeGoalKeeper
        ShotsHome.text = listData?.get(0)?.HomeShots
        DefenseHome.text = listData?.get(0)?.HomeDefense
        ForwardHome.text = listData?.get(0)?.HomeForward
        SubtitutesHome.text = listData?.get(0)?.HomeSubstitutes
        MidfieldHome.text = listData?.get(0)?.HomeMidfield
        HomeName = listData?.get(0)?.HomeName.toString()
        AwayName = listData?.get(0)?.AwayName.toString()
        HomeScore = listData?.get(0)?.HomeScore.toString()
        AwayScore = listData?.get(0)?.AwayScore.toString()
        MatchDate.text = listData?.get(0)?.DateEvent
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }


    override fun showTeamsList(listDataHome: List<Team>?, listDataAway: List<Team>?) {
        TeamHome = Team(listDataHome?.get(0)?.teamBadge)
        TeamAway = Team(listDataAway?.get(0)?.teamBadge)
        Glide.with(this).load(listDataHome?.get(0)?.teamBadge).into(ImgHome)
        Glide.with(this).load(listDataAway?.get(0)?.teamBadge).into(ImgAway)
    }


}