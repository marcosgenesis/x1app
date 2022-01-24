package com.example.x1um

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.Battles
import com.example.x1um.Model.User
import com.example.x1um.Model.Users

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val userAdapter = UserAdapter(ArrayList<User>(Users.fakeUsers()))
        val rv: RecyclerView = findViewById(R.id.searchRecycler);
        rv.adapter = userAdapter
    }
}