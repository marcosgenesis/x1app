package com.example.x1um.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.User
import com.example.x1um.R
import com.example.x1um.Services.BattleService
import com.example.x1um.Services.UserService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    lateinit var auth: FirebaseAuth
    lateinit var userService: UserService
    lateinit var battleService: BattleService
    var battlesList: ArrayList<Battle> = ArrayList()
    lateinit var userIntent: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        battleService = BattleService()
        userService = UserService()
        userIntent = User("", "", "", "", 0, 0)

        showDuels()

        onClickHistory()
        onClickSearchBattle()
        getNameUser()
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

    private fun getNameUser() {
        val nameOfUser: TextView = findViewById(R.id.nameOfUser)
        userService.getUser {
            user ->
            userIntent = user
            nameOfUser.text = userIntent.name
        }

    }

    private fun showDuels() {
        battleService.listBattles() {
                battles, points, games, user ->
            battlesList = battles
            val battleAdapter = BattleAdapter(battlesList)
            val rv:RecyclerView = findViewById(R.id.battlesRecycler);
            rv.adapter = battleAdapter
        }
    }
}