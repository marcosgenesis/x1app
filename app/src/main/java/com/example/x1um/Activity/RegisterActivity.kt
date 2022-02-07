package com.example.x1um.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.x1um.R
import com.example.x1um.Services.AuthFirebase


class RegisterActivity : AppCompatActivity() {
    lateinit var authService: AuthFirebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        authService = AuthFirebase()

        onClickRegisterButton()
        onClickLoginLink()
    }

    private fun onClickRegisterButton() {
        val editTextName: EditText = findViewById(R.id.input_name)
        val editTextUsername: EditText = findViewById(R.id.input_username)
        val editTextEmail: EditText = findViewById(R.id.input_email)
        val editTextPassword: EditText = findViewById(R.id.input_password)

        val name = editTextName.getText()
        val username = editTextUsername.getText()
        val email = editTextEmail.getText()
        val password = editTextPassword.getText()

        val buttonOnRegister = findViewById<Button>(R.id.register_button)
        buttonOnRegister.setOnClickListener {
            authService.registerUser(name.toString(), username.toString(), email.toString(), password.toString()) { resultSuccessful ->
                if(resultSuccessful) {
                    val activityLogin = Intent(this, LoginActivity::class.java)
                    startActivity(activityLogin)
                }
            }
        }
    }

    private fun onClickLoginLink(){
        val buttonNavigationLogin: TextView = findViewById(R.id.already_login_button)
        buttonNavigationLogin.setOnClickListener{
            val activityLogin = Intent(this, LoginActivity::class.java)
            startActivity(activityLogin)
        }
    }
}