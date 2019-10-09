package com.example.nbaplayers.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.nbaplayers.R
import com.example.nbaplayers.models.Player
import kotlinx.android.synthetic.main.activity_player_edit.*

class PlayerEditActivity : AppCompatActivity() {

    // This value will get re-assigned if the action is edit player
    var playerId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_edit)

        setupView()
    }

    fun setupView() {
        val action = intent?.action

        actionBar?.apply {
            setTitle(R.string.player_create_title)
        }

        when (action) {
            ACTION_EDIT_PLAYER -> {
                intent?.getParcelableExtra<Player>(EXTRA_PLAYER_KEY)?.let {
                    et_born.setText(it.born)
                    et_hometown.setText(it.hometown)
                    et_url.setText(it.url)
                    et_first_name.setText(it.firstName)
                    et_last_name.setText(it.lastName)
                    actionBar?.setTitle(R.string.player_edit_title)
                    playerId = it.id
                }

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                return true
            }
            R.id.action_save -> return handleSaveAction()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_player_edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun handleSaveAction(): Boolean {
        val firstName = et_first_name.text.toString()
        val lastName = et_last_name.text.toString()
        val born = et_born.text.toString()
        val hometown = et_hometown.text.toString()
        val url = et_url.text.toString()

        Intent(intent?.action).apply {
            putExtra(
                EXTRA_PLAYER_KEY,
                Player(
                    firstName = firstName,
                    lastName = lastName,
                    hometown = hometown,
                    born = born,
                    url = url,
                    id = playerId
                )
            )
            setResult(Activity.RESULT_OK, this)
        }
        finish()
        return true
    }
}

