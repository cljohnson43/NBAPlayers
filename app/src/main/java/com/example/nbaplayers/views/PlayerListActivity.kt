package com.example.nbaplayers.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbaplayers.R
import com.example.nbaplayers.adapters.PlayersAdapter
import com.example.nbaplayers.models.Player
import com.example.nbaplayers.viewmodels.PlayersViewModel
import com.example.nbaplayers.utils.Logger
import kotlinx.android.synthetic.main.activity_player_list.*

class PlayerListActivity : AppCompatActivity() {

    val playersViewModel: PlayersViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(PlayersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_list)

        playersViewModel.getPlayers().observe(this, object : Observer<List<Player>> {
            override fun onChanged(playerList: List<Player>?) {
                playerList?.forEach { Logger.log("New Player: $it") }
            }
        }
        )

        playersViewModel.getPlayers().value?.let {
            rv_player_list.adapter = PlayersAdapter(it)
            rv_player_list.layoutManager = LinearLayoutManager(this)
        }

    }
}
