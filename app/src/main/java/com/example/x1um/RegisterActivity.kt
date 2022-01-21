package com.example.x1um

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.x1um.Model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    val db = Firebase.firestore
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
            registerUser(name.toString(), username.toString(), email.toString(), password.toString())
        }
    }

    private fun onClickLoginLink(){
        val buttonNavigationLogin: TextView = findViewById(R.id.already_login_button)
        buttonNavigationLogin.setOnClickListener{
            val activityLogin = Intent(this, LoginActivity::class.java)
            startActivity(activityLogin)
        }
    }

    private fun registerUser(name: String, username: String, email: String, password: String) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password).
        addOnCompleteListener { task: Task<AuthResult> ->
            if(task.isSuccessful){
                val user = User(auth.currentUser?.uid.toString(), name, username, email)

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        val activityLogin = Intent(this, LoginActivity::class.java)
                        startActivity(activityLogin)
                    }
                    .addOnFailureListener { e ->
                        Log.w("Register user error", "Error ao cadastrar usuário")
                    }
            }else{
                Log.w("Register error", "Erro ao realizar cadastro",)
            }
        }
    }
}