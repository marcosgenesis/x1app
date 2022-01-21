package com.example.x1um

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        onClickHistoryButton()
    }

    private fun onClickHistoryButton (){
        val buttonHistory: ImageView = findViewById(R.id.historyIcon)
        buttonHistory.setOnClickListener{
            val activityHome = Intent(this, BattleActivity::class.java)
            startActivity(activityHome)
        }
    }
}