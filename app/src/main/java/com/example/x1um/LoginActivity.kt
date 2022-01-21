package com.example.x1um

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    val db = Firebase.firestore
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        onClickLoginButton()
        onClickRegisterLink()
    }

    private fun onClickLoginButton (){
        val editTextEmail : TextView = findViewById(R.id.input_email)
        val editTextPassword : TextView = findViewById(R.id.input_password)

        val emailInput = editTextEmail.getText()
        val passwordInput = editTextPassword.getText()

        println("Email: ")
        println(emailInput)
        println("Password: ")
        println(passwordInput)

        val buttonRegister: Button = findViewById(R.id.button_login)
        buttonRegister.setOnClickListener{
            loginUser(emailInput.toString(), passwordInput.toString())
        }
    }

    private fun onClickRegisterLink (){
        val buttonNavigationRegister: Button = findViewById(R.id.button_register)
        buttonNavigationRegister.setOnClickListener{
            val activityRegister = Intent(this, RegisterActivity::class.java)
            startActivity(activityRegister)
        }
    }

    private fun loginUser(email: String, password: String) {
        println(email)
        println(password)
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    db.collection("users").whereEqualTo("email", user?.email).get().addOnSuccessListener { result ->
                        val activityMain = Intent(this, MainActivity::class.java)
                        startActivity(activityMain)
                    }.addOnFailureListener { error ->
                        Log.w("User login error", "Error ao obter usuário", error)
                    }
                } else {
                    Log.w("Login error", "Erro ao realizar login",)
                }
            }
    }
}