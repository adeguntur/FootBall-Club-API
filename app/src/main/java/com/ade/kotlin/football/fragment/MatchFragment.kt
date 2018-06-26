package com.ade.kotlin.football.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ade.kotlin.football.DetailActivity
import com.ade.kotlin.football.R
import com.ade.kotlin.football.R.array.main
import com.ade.kotlin.football.R.string.*
import com.ade.kotlin.football.adapter.AdapterMain
import com.ade.kotlin.football.api.API
import com.ade.kotlin.football.key.KEY
import com.ade.kotlin.football.model.Favorite
import com.ade.kotlin.football.model.Match
import com.ade.kotlin.football.presenter.Past
import com.ade.kotlin.football.presenter.Next
import com.ade.kotlin.football.view.MainInterface
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*


class MatchFragment : Fragment() , AnkoComponent<Context>, MainInterface {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var listMatch: RecyclerView

    private lateinit var spinner: Spinner

    private lateinit var adapterMain: AdapterMain


    private lateinit var pastPresenter: Past
    private lateinit var nextPresenter: Next
    private var events: MutableList<Match> = mutableListOf()
    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = API()
        val gson = Gson()
        pastPresenter = Past(this, request, gson)
        nextPresenter = Next(this, request, gson)

        val spinnerItems = resources.getStringArray(main)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if(spinner.selectedItem == getString(PastMatch)){
                    adapterMain = AdapterMain(events) {
                        startActivity<DetailActivity>(
                                KEY.HOME_ID to it.HomeId,
                                KEY.AWAY_ID to it.AwayId,
                                KEY.EVENT_ID to it.EventId)
                    }
                    listMatch.adapter = adapterMain

                    pastPresenter.getPastMatchList(getString(LeagueId))

                    swipeRefresh.onRefresh {
                        pastPresenter.getPastMatchList(getString(LeagueId))
                    }
                }else{
                    adapterMain = AdapterMain(events) {
                        startActivity<DetailActivity>(
                                KEY.HOME_ID to it.HomeId,
                                KEY.AWAY_ID to it.AwayId,
                                KEY.EVENT_ID to it.EventId)
                    }
                    listMatch.adapter = adapterMain

                    nextPresenter.getNextMatchList(getString(LeagueId))

                    swipeRefresh.onRefresh {
                        nextPresenter.getNextMatchList(getString(LeagueId))
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View =  with(ui){
            linearLayout{
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL

                spinner = spinner(){
                    id = R.id.spinner
                }

                swipeRefresh = swipeRefreshLayout{
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout{
                        lparams(width = matchParent, height = wrapContent)

                        listMatch = recyclerView{
                            id = R.id.ListMatch
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {

                        }.lparams{
                            centerHorizontally()
                        }
                    }
                }
            }


    }



    private fun View.visible(){
        visibility = View.VISIBLE
    }

    private fun View.invisible(){
        visibility = View.INVISIBLE
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(listData: List<Match>?) {
        favorites.clear()
        swipeRefresh.isRefreshing = false
        events.clear()
        listData?.let {
            events.addAll(listData)
            adapterMain.notifyDataSetChanged()
        } ?: toast(getString(R.string.no_data))
    }
}
