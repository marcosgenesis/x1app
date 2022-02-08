package com.example.x1um.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.x1um.Model.User
import com.example.x1um.R
import com.example.x1um.Services.BattleService
import com.example.x1um.Services.UserService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DuelActivity : AppCompatActivity(),OnMapReadyCallback {
    lateinit var txtGoals: TextView
    lateinit var txtAgainstGoals: TextView
    lateinit var userService: UserService
    lateinit var battleService: BattleService
    lateinit var userIntent: User
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duel)
        userService = UserService()
        battleService = BattleService()

        val oponent:User = intent.getSerializableExtra("user") as User;

        txtGoals = findViewById(R.id.proGoals)
        txtAgainstGoals = findViewById(R.id.againstGoals)
        val txtOponentName = findViewById<TextView>(R.id.oponentDuelName)
        val txtOpoenentInitialLetter = findViewById<TextView>(R.id.oponentInitialLetters)

        txtOpoenentInitialLetter.setText(oponent.name.substring(0,2).uppercase())
        txtOponentName.setText(oponent.name)
        txtAgainstGoals.setText(0.toString())
        txtGoals.setText(0.toString())


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.duelmap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        onClickHome()
        onClickHistory()
        getNameUser()


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapClickListener { result ->
            val lat = result.latitude
            val long = result.longitude

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(lat, long))
                    .title("Location"))
        }
    }

    fun scoreGoal(view:View, user: User){
        var goal = txtGoals.text.toString();
        txtGoals.setText((goal.toInt() + 1).toString())

    }

    fun scoreOponentGoal(view:View, user: User){
        var goal = txtAgainstGoals.text.toString();
        txtAgainstGoals.setText((goal.toInt() + 1).toString())
    }

    private fun getNameUser() {
        val txtMyInitialLetter = findViewById<TextView>(R.id.MyInitialLetters)
        val txtMyName = findViewById<TextView>(R.id.myNameInDuel)
        userService.getUser {
                user ->
            userIntent = user
            txtMyName.text = userIntent.name
            txtMyInitialLetter.text = userIntent.name.substring(0,2).toString().uppercase()
        }

    }

    private fun onClickHome(){
        val buttonNavigationSearchBattle: ImageView = findViewById(R.id.homeIconDuel)
        buttonNavigationSearchBattle.setOnClickListener{
            val activitySearchBattle= Intent(this, MainActivity::class.java)
            startActivity(activitySearchBattle)
        }
    }

    private fun onClickHistory(){
        val buttonNavigationHistory: ImageView = findViewById(R.id.historyIconDuel)
        buttonNavigationHistory.setOnClickListener{
            val activityBattle= Intent(this, BattleActivity::class.java)
            startActivity(activityBattle)
        }
    }

}