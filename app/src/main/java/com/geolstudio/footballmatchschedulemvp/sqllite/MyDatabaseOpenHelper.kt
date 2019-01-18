package com.geolstudio.footballmatchschedulemvp.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorites.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance =
                        MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            DataMatches.TABLE_MATCHES, true,
            DataMatches.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DataMatches.ID_EVENT to TEXT + UNIQUE,
            DataMatches.DATE_EVENT to TEXT,
            DataMatches.TIME_EVENT to TEXT,

            DataMatches.TEAM1_ID to TEXT,
            DataMatches.TEAM1_NAME to TEXT,
            DataMatches.TEAM1_BADGE to TEXT,
            DataMatches.TEAM1_GOALDETAIL to TEXT,
            DataMatches.TEAM1_GK to TEXT,
            DataMatches.TEAM1_DEF to TEXT,
            DataMatches.TEAM1_MID to TEXT,
            DataMatches.TEAM1_FW to TEXT,
            DataMatches.TEAM1_SUB to TEXT,
            DataMatches.TEAM1_SHOT to TEXT,
            DataMatches.TEAM1_SCORE to TEXT,

            DataMatches.TEAM2_ID to TEXT,
            DataMatches.TEAM2_NAME to TEXT,
            DataMatches.TEAM2_BADGE to TEXT,
            DataMatches.TEAM2_GOALDETAIL to TEXT,
            DataMatches.TEAM2_GK to TEXT,
            DataMatches.TEAM2_DEF to TEXT,
            DataMatches.TEAM2_MID to TEXT,
            DataMatches.TEAM2_FW to TEXT,
            DataMatches.TEAM2_SUB to TEXT,
            DataMatches.TEAM2_SHOT to TEXT,
            DataMatches.TEAM2_SCORE to TEXT
        )

        db.createTable(
            DataTeams.TABLE_TEAMS, true,
            DataTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DataTeams.TEAM_ID to TEXT,
            DataTeams.TEAM_NAME to TEXT,
            DataTeams.TEAM_YEAR to TEXT,
            DataTeams.TEAM_STADIUM to TEXT,
            DataTeams.TEAM_DESC to TEXT,
            DataTeams.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(DataMatches.TABLE_MATCHES, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)