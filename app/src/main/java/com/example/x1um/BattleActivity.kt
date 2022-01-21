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
    var battles: List<Battle> = ArrayList<Battle>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        showBattlesOfUser()
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
                            println(documentReference.size())
                            for(document in documentReference) {
                                println("Batalhas que sou usuário::: ")
                                println(document.data)
                            }
                        }

                    db.collection("battles").whereEqualTo("oponentName", document.data["name"]).get()
                        .addOnSuccessListener { documentReference ->
                            println("Deu certo agora !! ")
                            for(document in documentReference) {
                                println("Batalhas que sou oponente::: ")
                                println(document.data)
                            }
                        }
                }
                //println(user.name)
            }
            .addOnFailureListener { exception ->
                Log.w("Erro: ", "Error getting documents: ", exception)
            }


        println("Saí do DB")
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
}