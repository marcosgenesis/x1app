package com.example.x1um

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.x1um.Model.Battle
import com.example.x1um.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class BattleActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    lateinit var auth: FirebaseAuth
    lateinit var battles: ArrayList<Battle>
    //var battles: ArrayList<Battle> = ArrayList<Battle>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        battles = ArrayList()

        showBattlesOfUser()
        printBattles()
    }

    private fun showBattlesOfUser() {
        auth = Firebase.auth
        println("Current user: ")
        println(auth.currentUser)

        var user = User("", "", "", "");

        db.collection("users")
            .whereEqualTo("email", auth.currentUser?.email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    user.id = document.data["id"] as String
                    user.name = document.data["name"] as String
                    user.username = document.data["username"] as String
                    user.email = document.data["email"] as String
                    //Log.d("Deu bom: ", "${document.id} => ${document.data}")
                    println(document.data["name"])
                    db.collection("battles").whereEqualTo("myName", document.data["name"]).get()
                        .addOnSuccessListener { documentReference ->
                            println("Deu certo!! ")
                            for(document in documentReference) {
                                println("Batalhas que sou usuário::: ")
                                println(document.data)
                                var battle = Battle();
                                battle.setMyName(document.data["myName"] as String?)
                                battle.setOponentName(document.data["oponentName"] as String?)
                                battle.setGoalsPro(4)
                                battle.setGoalsAgainst(1)
                                battle.setFinished(true)
                                battle.setWinner(document.data["winner"] as String?)
                                println("Battle: ")
                                println(battle.getMyName())
                                battles.add(battle)
                                println("Nome do ultimo")
                                println(battles.get(battles.size - 1).getMyName())
                                println("Size do battles: ")
                                println(battles.size)
                            }
                        }

                    db.collection("battles").whereEqualTo("oponentName", document.data["name"]).get()
                        .addOnSuccessListener { documentReference ->
                            println("Deu certo agora !! ")
                            for(document in documentReference) {
                                println("Batalhas que sou oponente::: ")
                                println(document.data)
                                var battle = Battle();
                                battle.setMyName(document.data["myName"] as String?)
                                battle.setOponentName(document.data["oponentName"] as String?)
                                battle.setGoalsPro(6)
                                battle.setGoalsAgainst(2)
                                battle.setFinished(true)
                                battle.setWinner(document.data["winner"] as String?)
                                println("Battle: ")
                                println(battle.getMyName())
                                battles.add(battle)
                                println("Nome do ultimo")
                                println(battles.get(battles.size - 1).getMyName())
                                println("Size do battles: ")
                                println(battles.size)
                            }
                        }
                }
                //println(user.name)
            }

        //println(user.email)

        /*
        db.collection("battles").whereEqualTo("myName", user.name).get()
            .addOnSuccessListener { documentReference ->
                val activityLogin = Intent(this, LoginActivity::class.java)
                startActivity(activityLogin)
            }
            .addOnFailureListener { e ->
                db.collection("battles").whereEqualTo("oponentName", user.name).get()
                    .addOnSuccessListener { documentReference ->
                        val activityLogin = Intent(this, LoginActivity::class.java)
                        startActivity(activityLogin)
                    }
                    .addOnFailureListener { e ->
                        Log.w("Duels user error", "Error ao listar duelos do usuário")
                    }
            }
         */
    }

    private fun printBattles() {
        println("Entrei no meu método: ")
        println(battles.size)
    }
}