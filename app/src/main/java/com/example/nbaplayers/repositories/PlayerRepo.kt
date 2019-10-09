package com.example.nbaplayers.repositories

import android.content.Context
import com.example.nbaplayers.db.PlayersDb
import com.example.nbaplayers.db.PlayersDbHelper
import com.example.nbaplayers.models.Player

class PlayerRepo(context: Context) {
    private val db: PlayersDb by lazy {
        PlayersDb(context)
    }

    fun getAllPlayers(): List<Player> = db.getAllPlayers()
    fun insertPlayer(player: Player): Long = db.insertPlayer(player)
    fun deletePlayer(player: Player): Int = db.deletePlayer(player)
}