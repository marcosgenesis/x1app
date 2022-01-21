package com.example.x1um

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.x1um.Model.Battle
import com.google.firebase.firestore.FirebaseFirestore

class CreateBattleActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_battle)
    }

    private fun onClickCreateDuel(){

    }

    /*
    private fun createDuel(myName: String, oponentName: String, goalsPro: Int, goalsAgainst: Int, isFinished: Boolean, winner: String){
        val battle = Battle(myName, oponentName, goalsPro, goalsAgainst, isFinished, winner);
        db.collection("duels")
            .add(battle)
            .addOnSuccessListener { documentReference ->
                val activityDuel = Intent(this, BattleActivity::class.java)
                startActivity(activityDuel)
            }
            .addOnFailureListener { e ->
                Log.w("Duel user error", "Error ao cadastrar duelo")
            }.addOnFailureListener { e ->
                Log.w("Duel user error", "Error ao cadastrar duelo")
            }
    }
    */
}