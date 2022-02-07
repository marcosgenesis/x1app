package com.example.x1um.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.User
import com.example.x1um.Model.Mock.Users

import android.content.Intent
import android.widget.ImageView
import com.example.x1um.OnUserClickListener
import com.example.x1um.R
import com.example.x1um.UserAdapter


class SearchActivity : AppCompatActivity(), OnUserClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        onClickSearchBattle()
        onClickHistory()
        onClickHome()

        val userAdapter = UserAdapter(ArrayList<User>(Users.fakeUsers()),this)
        val rv: RecyclerView = findViewById(R.id.searchRecycler);
        rv.adapter = userAdapter

    }

    override fun onItemClick(item: User, position: Int) {
        val intent = Intent(this, DuelActivity::class.java).apply {
            putExtra("user", item)
        }
        startActivity(intent)
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

}