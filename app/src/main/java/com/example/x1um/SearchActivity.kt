package com.example.x1um

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.Battles
import com.example.x1um.Model.User
import com.example.x1um.Model.Users
import android.R.attr.data

import android.content.Intent




class SearchActivity : AppCompatActivity(),OnUserClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

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


}