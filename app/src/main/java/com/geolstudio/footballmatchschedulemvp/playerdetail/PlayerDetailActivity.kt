package com.geolstudio.footballmatchschedulemvp.playerdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.model.players.DataPlayers
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = intent.getParcelableExtra<DataPlayers>("data")

        title = data.player_name

        if (data.player_image != null) {
            Glide.with(applicationContext).load(data.player_image).into(iv_player_image)
        } else {
            Glide.with(applicationContext).load(R.drawable.notfound).into(iv_player_image)
        }

        var weight = "-"
        var height = "-"
        if(data.player_weight.isNotEmpty()) {
            weight = data.player_weight
        }
        if(data.player_height.isNotEmpty()) {
            height = data.player_height
        }
        tv_player_weight.text = weight
        tv_player_height.text = height
        tv_player_position.text = data.player_position
        tv_player_desc.text = data.player_desc
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
