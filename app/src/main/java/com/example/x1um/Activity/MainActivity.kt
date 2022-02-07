package com.example.x1um.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.Mock.Battles
import com.example.x1um.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val battleAdapter = BattleAdapter(ArrayList<Battle>(Battles.fakeBattles()))
        val rv:RecyclerView = findViewById(R.id.battlesRecycler);
        rv.adapter = battleAdapter

        onClickHistory()
        onClickSearchBattle()
    }

    private fun onClickSearchBattle(){
        val buttonNavigationSearchBattle: ImageView = findViewById(R.id.battleIcon)
        buttonNavigationSearchBattle.setOnClickListener{
            val activitySearchBattle= Intent(this, SearchActivity::class.java)
            startActivity(activitySearchBattle)
        }
    }

    private fun onClickHistory(){
        val buttonNavigationHistory: ImageView = findViewById(R.id.historyIcon)
        buttonNavigationHistory.setOnClickListener{
            val activityBattle= Intent(this, BattleActivity::class.java)
            startActivity(activityBattle)
        }
    }
}