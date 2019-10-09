package com.example.nbaplayers.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbaplayers.models.Player
import com.example.nbaplayers.repositories.PlayerRepo

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

class PlayersViewModel(application: Application) : AndroidViewModel(application) {
    private val playerList: MutableLiveData<List<Player>> by lazy {
        MutableLiveData<List<Player>>().default(loadPlayers())
    }

    private val playerRepo: PlayerRepo by lazy {
        PlayerRepo(getApplication())
    }

    fun getPlayers(): LiveData<List<Player>> = playerList

    fun loadPlayers(): List<Player> {

        val list: List<Player> = listOf(
            Player(
                firstName = "Michael",
                lastName = "Jordan",
                url = "www.thegreatest.ever",
                hometown = "nc",
                born = "12/21/1985"
            ),
            Player(
                firstName = "Charles",
                lastName = "Barkley",
                url = "www.thegoat.com",
                hometown = "nc",
                born = "12/21/1985"
            ),
            Player(
                firstName = "Hakeem",
                lastName = "Olijaun",
                url = "www.thedream.com",
                hometown = "nc",
                born = "12/21/1985"
            ),
            Player(
                firstName = "Lebron",
                lastName = "James",
                url = "www.theking.le",
                hometown = "nc",
                born = "12/21/1985"
            )
        )

        list.forEach{it -> playerRepo.insertPlayer(it)}

        val players = playerRepo.getAllPlayers()

        playerRepo.deletePlayer(players[0])

        return playerRepo.getAllPlayers()
    }
}