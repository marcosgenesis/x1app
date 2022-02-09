package com.example.x1um.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.User
import com.example.x1um.OnUserClickListener
import com.example.x1um.R
import com.example.x1um.Services.BattleService
import com.example.x1um.Services.UserService
import com.example.x1um.UserAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SearchActivity : AppCompatActivity(), OnUserClickListener {
    var db = FirebaseFirestore.getInstance()
    lateinit var auth: FirebaseAuth
    var countOfVictorys: Int = 0
    var points: Int = 0
    private var users: MutableList<User>? = null
    //val users: List<User> = listOf( User("", "32", "", "",))
    lateinit var userService: UserService
    lateinit var battleService: BattleService
    lateinit  var battlesList: ArrayList<Battle>
    lateinit var userList: ArrayList<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        battleService = BattleService()
        userService = UserService()

        userList = ArrayList()

        onClickSearchBattle()
        onClickHistory()
        onClickHome()

        showRanking()
        onSearch()
    }

    override fun onItemClick(item: User, position: Int) {
        val intent = Intent(this, DuelActivity::class.java).apply {
            putExtra("user", item)
        }
        startActivity(intent)
    }

    private fun onSearch() {
        val personName: EditText = findViewById(R.id.editTextTextPersonName)

        println("Person Name aquiii: ")
        println(personName.text.toString())

//        personName.setOnEditorActionListener { v, actionId, event ->
//            var handled = false
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                println(personName.text.toString())
//                handled = true
//            }
//            handled
//        }

    }

    private fun onClickSearchBattle(){
        val buttonNavigationSearchBattle: ImageView = findViewById(R.id.battleIconSearch)
        buttonNavigationSearchBattle.setOnClickListener{
            val activitySearchBattle= Intent(this, SearchActivity::class.java)
            startActivity(activitySearchBattle)
        }
    }

    private fun onClickHistory(){
        val buttonNavigationHistory: ImageView = findViewById(R.id.historyIconSearch)
        buttonNavigationHistory.setOnClickListener{
            val activityBattle= Intent(this, BattleActivity::class.java)
            startActivity(activityBattle)
        }
    }

    private fun onClickHome(){
        val buttonNavigationHistory: ImageView = findViewById(R.id.homeIconSearch)
        buttonNavigationHistory.setOnClickListener{
            val activityMain= Intent(this, MainActivity::class.java)
            startActivity(activityMain)
        }
    }

    private fun showRanking() {
        battleService.listBattles() {
            battles, points, games, user ->
            battlesList = battles

            for (battle in battlesList) {
                var userReturn = User("", "", "", "", 0, 0,  0)
                userReturn.points = user.points
                userReturn.username = user.username
                userReturn.games = battlesList.size
                userReturn.name = battle.getOponentName().toString()
                println("username: ")
                println(userReturn.username)
                userList.add(userReturn)
            }

            val userAdapter = UserAdapter(userList,this)
            val rv: RecyclerView = findViewById(R.id.searchRecycler);
            rv.adapter = userAdapter
        }
    }
}