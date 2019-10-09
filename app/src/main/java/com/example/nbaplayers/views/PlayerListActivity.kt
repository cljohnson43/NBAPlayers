package com.example.nbaplayers.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbaplayers.R
import com.example.nbaplayers.adapters.PlayersAdapter
import com.example.nbaplayers.models.Player
import com.example.nbaplayers.viewmodels.PlayersViewModel
import com.example.nbaplayers.utils.Logger
import kotlinx.android.synthetic.main.activity_player_list.*

const val ACTION_CREATE_PLAYER = "action.create.player"
const val ACTION_EDIT_PLAYER = "action.edit.player"
const val REQUEST_CREATE_PLAYER = 1
const val REQUEST_EDIT_PLAYER = 2
const val EXTRA_PLAYER_KEY = "extra.player"

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

    fun onClick(view: View) {
        when (view.id) {
            R.id.fab_add_player -> handleAddClick()
        }
    }

    fun handleAddClick() {
        Intent(this, PlayerEditActivity::class.java).apply {
            setAction(ACTION_CREATE_PLAYER)
            startActivityForResult(this, REQUEST_CREATE_PLAYER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val player = data?.getParcelableExtra<Player>(EXTRA_PLAYER_KEY)

        when (requestCode) {
            REQUEST_CREATE_PLAYER -> {Logger.log(player.toString())}
            REQUEST_EDIT_PLAYER -> {Logger.log(player.toString())}
        }
    }
}
