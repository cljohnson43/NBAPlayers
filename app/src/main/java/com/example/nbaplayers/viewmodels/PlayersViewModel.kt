package com.example.nbaplayers.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbaplayers.models.Player

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

class PlayersViewModel() : ViewModel() {
    val playerList: MutableLiveData<List<Player>> by lazy {
        MutableLiveData<List<Player>>().default(loadPlayers())
    }

    fun getPlayers(): LiveData<List<Player>> = playerList

    fun loadPlayers(): List<Player> {
        return listOf(
            Player(firstName = "Michael", lastName = "Jordan", url="www.thegreatest.ever", hometown="nc", born="12/21/1985"),
            Player(firstName = "Charles", lastName = "Barkley", url="www.thegoat.com", hometown="nc", born="12/21/1985"),
            Player(firstName = "Hakeem", lastName = "Olijaun", url="www.thedream.com", hometown="nc", born="12/21/1985"),
            Player(firstName = "Lebron", lastName = "James", url="www.theking.le", hometown="nc", born="12/21/1985")
            )
    }
}