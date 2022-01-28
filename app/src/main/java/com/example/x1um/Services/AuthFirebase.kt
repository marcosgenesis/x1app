package com.example.x1um.Services

import android.util.Log
import com.example.x1um.Model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AuthFirebase {
    val db = Firebase.firestore
    lateinit var auth: FirebaseAuth

    fun loginUser(email: String, password: String,  setResultSuccessful: (resultSuccessful:  Boolean) -> Unit) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    val user = auth.currentUser
                    db.collection("users").whereEqualTo("email", user?.email).get().addOnSuccessListener { result ->
                        setResultSuccessful(true)
                    }.addOnFailureListener { error ->
                        setResultSuccessful(false)
                        Log.w("User login error", "Error ao obter usuário", error)
                    }
                }
            }
    }

    fun registerUser(name: String, username: String, email: String, password: String, setResultSuccessful: (resultSuccessful:  Boolean) -> Unit) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password).
        addOnCompleteListener { task: Task<AuthResult> ->
            if(task.isSuccessful){
                val user = User(auth.currentUser?.uid.toString(), name, username, email)

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        setResultSuccessful(true)
                        Log.w("Register user successful", "Usuário cadastrado com sucesso")
                    }
                    .addOnFailureListener { error ->
                        setResultSuccessful(false)
                        Log.w("Register user error", "Error ao cadastrar usuário")
                    }
            }else{
                setResultSuccessful(false)
                Log.w("Register error", "Erro ao realizar cadastro",)
            }
        }
    }
}