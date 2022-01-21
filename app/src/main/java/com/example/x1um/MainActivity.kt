package com.example.x1um

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.Battles

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val battleAdapter = BattleAdapter(ArrayList<Battle>(Battles.fakeBattles()))
        val rv:RecyclerView = findViewById(R.id.battlesRecycler);
        rv.adapter = battleAdapter
    }
}