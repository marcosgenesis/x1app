package com.example.x1um.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.Mock.Users
import com.example.x1um.Model.User
import com.example.x1um.OnUserClickListener
import com.example.x1um.R
import com.example.x1um.Services.BattleService
import com.example.x1um.UserAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class BattleActivity : AppCompatActivity(), OnUserClickListener {
    var db = FirebaseFirestore.getInstance()
    lateinit var auth: FirebaseAuth
    lateinit var battleService: BattleService
    lateinit  var battlesList: ArrayList<Battle>
    var gamesUser: Int = 0
    var pointsUser: Int = 0
    lateinit var userList: ArrayList<User>
    lateinit var champions: ArrayList<String>
    var champion by Delegates.notNull<Int>()
    lateinit var nameOfChampion: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        battleService = BattleService()
        userList = ArrayList()
        champions = ArrayList()

        champion = 0
        nameOfChampion = ""

        val userAdapter = UserAdapter(ArrayList<User>(Users.fakeRankingUsers()),this)
        val rv: RecyclerView = findViewById(R.id.rankingRecycler);
        rv.adapter = userAdapter

        onClickSearchBattle()
        onClickHistory()
        onClickHome()

        showRankingUsers()

        //battles = ArrayList()
        //showBattlesOfUser()
    }

    private fun onClickSearchBattle(){
        val buttonNavigationSearchBattle: ImageView = findViewById(R.id.battleIconRanking)
        buttonNavigationSearchBattle.setOnClickListener{
            val activitySearchBattle= Intent(this, SearchActivity::class.java)
            startActivity(activitySearchBattle)
        }
    }

    private fun onClickHistory(){
        val buttonNavigationHistory: ImageView = findViewById(R.id.historyIconRanking)
        buttonNavigationHistory.setOnClickListener{
            val activityBattle= Intent(this, BattleActivity::class.java)
            startActivity(activityBattle)
        }
    }

    private fun onClickHome(){
        val buttonNavigationHistory: ImageView = findViewById(R.id.homeIconRanking)
        buttonNavigationHistory.setOnClickListener{
            val activityMain= Intent(this, MainActivity::class.java)
            startActivity(activityMain)
        }
    }

    private fun showRankingUsers() {
        battleService.listBattles() {
                battles, points, games, user ->
            battlesList = battles

            for (battle in battlesList) {
                var userReturn = User("", "", "", "", 0, 0,  0)
                userReturn.points = user.points
                userReturn.username = user.username
                userReturn.games = battlesList.size
                userReturn.name = battle.getOponentName().toString()

                userList.add(userReturn)
            }

//            userList.sortBy {
//                it.points
//            }

            for (user in userList) {
               println(user.points)
            }



            val userAdapter = UserAdapter(userList,this)
            val rv: RecyclerView = findViewById(R.id.rankingRecycler);
            rv.adapter = userAdapter
        }
    }

    private fun showRanking() {
        battleService.listBattles() {
                battles, points, games, user ->
            battlesList = battles

            for (battle in battlesList) {
                var userReturn = User("", "", "", "", 0, 0, 0)
                userReturn.points = user.points
                userReturn.username = user.username
                userReturn.games = battlesList.size
                userReturn.name = battle.getOponentName().toString()
                println("username: ")
                println(user.username)
                userList.add(userReturn)

                println(userList)
            }

            val userAdapter = UserAdapter(userList,this)
            val rv: RecyclerView = findViewById(R.id.rankingRecycler);
            rv.adapter = userAdapter
        }
    }

    override fun onItemClick(item: User, position: Int) {
    }
}