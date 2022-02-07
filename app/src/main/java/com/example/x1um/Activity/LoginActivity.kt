package com.example.x1um.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.x1um.R
import com.example.x1um.Services.AuthFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var authService: AuthFirebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authService = AuthFirebase()

        onClickLoginButton()
        onClickRegisterLink()
    }

    private fun onClickLoginButton (){
        val editTextEmail : TextView = findViewById(R.id.input_email)
        val editTextPassword : TextView = findViewById(R.id.input_password)

        val emailInput = editTextEmail.getText()
        val passwordInput = editTextPassword.getText()

        val buttonRegister: Button = findViewById(R.id.button_login)
        buttonRegister.setOnClickListener{
            authService.loginUser(emailInput.toString(), passwordInput.toString()) { resultSuccessful ->
                if(resultSuccessful) {
                    val activityMain = Intent(this, MainActivity::class.java)
                    startActivity(activityMain)
                }
            }
        }
    }

    private fun onClickRegisterLink (){
        val buttonNavigationRegister: Button = findViewById(R.id.button_register)
        buttonNavigationRegister.setOnClickListener{
            val activityRegister = Intent(this, RegisterActivity::class.java)
            startActivity(activityRegister)
        }
    }




}