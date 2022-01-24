package com.example.x1um

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.x1um.Model.User

class DuelActivity : AppCompatActivity() {
    lateinit var txtGoals: TextView
    lateinit var txtAgainstGoals: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duel)

        val oponent:User = intent.getSerializableExtra("user") as User;
        val txtMyInitialLetter = findViewById<TextView>(R.id.MyInitialLetters)
        txtGoals = findViewById(R.id.proGoals)
        val txtMyName = findViewById<TextView>(R.id.myNameInDuel)
        txtAgainstGoals = findViewById(R.id.againstGoals)
        val txtOponentName = findViewById<TextView>(R.id.oponentDuelName)
        val txtOpoenentInitialLetter = findViewById<TextView>(R.id.oponentInitialLetters)

        Log.d(oponent.name, "namee")
        txtOpoenentInitialLetter.setText(oponent.name.substring(0,2).uppercase())
        txtOponentName.setText(oponent.name)
        txtAgainstGoals.setText(0.toString())
        txtGoals.setText(0.toString())
        txtMyInitialLetter.setText("MG")
        txtMyName.setText("Marcos Gênesis")

    }

    fun scoreGoal(view:View){
        var goal = txtGoals.text.toString();
        txtGoals.setText((goal.toInt() + 1).toString())
    }

    fun scoreOponentGoal(view:View){
        var goal = txtAgainstGoals.text.toString();
        txtAgainstGoals.setText((goal.toInt() + 1).toString())
    }
}