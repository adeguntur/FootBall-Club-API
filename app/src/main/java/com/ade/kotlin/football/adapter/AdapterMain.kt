package com.ade.kotlin.football.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ade.kotlin.football.R
import com.ade.kotlin.football.R.id.*
import com.ade.kotlin.football.model.Match
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.listeners.onClick

class AdapterMain (private val events: List<Match>, private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<AdapterMain.MatchViewHolder>(){

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItems(events[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = events.size

    class MatchUI : AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                cardView{
                    id = R.id.item
                    lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(16)
                        rightMargin = dip(16)
                        leftMargin = dip(16)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        padding = dip(16)

                        textView {
                            id = R.id.MatchDate
                            textColor = R.color.colorAccent
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = matchParent
                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL

                            textView {
                                id = R.id.NameHome
                                gravity = Gravity.CENTER
                            }.lparams {
                                width = matchParent
                                weight = 1f
                            }

                            textView {
                                id = R.id.ScoreMatch
                                gravity = Gravity.CENTER
                            }.lparams {
                                width = matchParent
                                weight = 1f
                            }

                            textView {
                                id = R.id.NameAway
                                gravity = Gravity.CENTER
                            }.lparams {
                                width = matchParent
                                weight = 1f
                            }
                        }
                    }
                }
            }
        }
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val homeName: TextView = view.find(NameHome)
        private val awayName: TextView = view.find(NameAway)
        private val score: TextView = view.find(ScoreMatch)
        private val matchDate: TextView = view.find(MatchDate)
        val cv: CardView = view.find(item)

        fun bindItems(events: Match, listener: (Match) -> Unit){
            homeName.text = events.HomeName
            awayName.text = events.AwayName
            if(events.HomeScore != null){
                score.text = events.HomeScore + " VS " + events.AwayScore
            }else{
                score.text = "? VS ?"
            }
            matchDate.text = events.DateEvent
            cv.onClick{
                listener(events)
            }
        }
    }
}