package com.ade.kotlin.football.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.ade.kotlin.football.model.FavoriteTeams
import org.jetbrains.anko.db.*

/**
 * Created by root on 2/6/18.
 */
class MyDatabaseOpenHelperTeams(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelperTeams? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelperTeams {
            if (instance == null) {
                instance = MyDatabaseOpenHelperTeams(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelperTeams
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavoriteTeams.TABLE_FAVORITE, true,
                FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeams.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeams.TEAM_NAME to TEXT,
                FavoriteTeams.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteTeams.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database1: MyDatabaseOpenHelperTeams
    get() = MyDatabaseOpenHelperTeams.getInstance(applicationContext)