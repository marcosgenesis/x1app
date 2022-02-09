package com.example.x1um.Services

import com.example.x1um.Model.Battle
import com.example.x1um.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserService {
    val db = Firebase.firestore
    lateinit var auth: FirebaseAuth

    fun getUser(resultSuccessful: (user: User) -> Unit) {
        auth = Firebase.auth

        var user = User("", "", "", "", 0, 0, 0);

        db.collection("users")
            .whereEqualTo("email", auth.currentUser?.email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    println(document)
                    user.id = document.data["id"] as String
                    user.name = document.data["name"] as String
                    user.username = document.data["username"] as String
                    user.email = document.data["email"] as String
                }
                resultSuccessful(user)
            }
    }

    fun getUserByName(name: String, resultSuccessful: (user: User) -> Unit) {

        var user = User("", "", "", "", 0, 0, 0);

        db.collection("users")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    println(document)
                    user.id = document.data["id"] as String
                    user.name = document.data["name"] as String
                    user.username = document.data["username"] as String
                    user.email = document.data["email"] as String
                }
                resultSuccessful(user)
            }
    }

    fun getUserByUserName(userName: String, resultSuccessful: (user: User) -> Unit) {

        var user = User("", "", "", "", 0, 0, 0);

        db.collection("users")
            .whereEqualTo("username", userName)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    println(document)
                    user.id = document.data["id"] as String
                    user.name = document.data["name"] as String
                    user.username = document.data["username"] as String
                    user.email = document.data["email"] as String
                }
                resultSuccessful(user)
            }
    }

}