package com.example.nbaplayers.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.nbaplayers.models.Player

object PlayersContract {
    object PlayersEntry : BaseColumns {
        const val TABLE_NAME = "players"
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_URL = "url"
        const val COLUMN_BORN = "born"
        const val COLUMN_HOMETOWN = "hometown"
    }
}

private const val SQL_CREATE_ENRTIES =
    "CREATE TABLE ${PlayersContract.PlayersEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${PlayersContract.PlayersEntry.COLUMN_FIRST_NAME} TEXT," +
            "${PlayersContract.PlayersEntry.COLUMN_LAST_NAME} TEXT," +
            "${PlayersContract.PlayersEntry.COLUMN_URL} TEXT," +
            "${PlayersContract.PlayersEntry.COLUMN_BORN} TEXT," +
            "${PlayersContract.PlayersEntry.COLUMN_HOMETOWN} TEXT)"

private const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS ${PlayersContract.PlayersEntry.TABLE_NAME}"

class PlayersDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENRTIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "Players.db"
        const val DB_VERSION = 1
    }
}

class PlayersDb(context: Context) {
    private val dbHelper: PlayersDbHelper by lazy {
        PlayersDbHelper(context)
    }

    fun insertPlayer(player: Player): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(PlayersContract.PlayersEntry.COLUMN_BORN, player.born)
            put(PlayersContract.PlayersEntry.COLUMN_HOMETOWN, player.hometown)
            put(PlayersContract.PlayersEntry.COLUMN_FIRST_NAME, player.firstName)
            put(PlayersContract.PlayersEntry.COLUMN_LAST_NAME, player.lastName)
            put(PlayersContract.PlayersEntry.COLUMN_URL, player.url)
        }
        return db?.insert(PlayersContract.PlayersEntry.TABLE_NAME, null, values) ?: -1
    }

    /*
    public Cursor query (boolean distinct,
                String table,
                String[] columns,
                String selection,
                String[] selectionArgs,
                String groupBy,
                String having,
                String orderBy,
                String limit)
     */
    fun getAllPlayers(): List<Player> {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            PlayersContract.PlayersEntry.COLUMN_BORN,
            PlayersContract.PlayersEntry.COLUMN_HOMETOWN,
            PlayersContract.PlayersEntry.COLUMN_URL,
            PlayersContract.PlayersEntry.COLUMN_FIRST_NAME,
            PlayersContract.PlayersEntry.COLUMN_LAST_NAME,
            BaseColumns._ID
        )

        val sortOrder = "${PlayersContract.PlayersEntry.COLUMN_LAST_NAME} asc"

        var cursor = db.query(
            PlayersContract.PlayersEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val playerList: MutableList<Player> = mutableListOf()

        with(cursor) {
            while (moveToNext()) {
                val firstName =
                    getString(getColumnIndexOrThrow(PlayersContract.PlayersEntry.COLUMN_FIRST_NAME))
                val lastName =
                    getString(getColumnIndexOrThrow(PlayersContract.PlayersEntry.COLUMN_LAST_NAME))
                val hometown =
                    getString(getColumnIndexOrThrow(PlayersContract.PlayersEntry.COLUMN_HOMETOWN))
                val born =
                    getString(getColumnIndexOrThrow(PlayersContract.PlayersEntry.COLUMN_BORN))
                val url =
                    getString(getColumnIndexOrThrow(PlayersContract.PlayersEntry.COLUMN_URL))
                val id =
                    getLong(getColumnIndexOrThrow(BaseColumns._ID))

                playerList.add(
                    Player(
                        firstName = firstName,
                        lastName = lastName,
                        hometown = hometown,
                        url = url,
                        born = born,
                        id = id
                    )
                )

            }
        }
        return playerList
    }
}