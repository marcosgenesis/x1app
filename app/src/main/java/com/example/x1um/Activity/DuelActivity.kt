package com.example.x1um.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.x1um.Model.Battle
import com.example.x1um.Model.User
import com.example.x1um.R
import com.example.x1um.Services.BattleService
import com.example.x1um.Services.UserService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DuelActivity : AppCompatActivity(),OnMapReadyCallback {
    lateinit var txtGoals: TextView
    lateinit var txtAgainstGoals: TextView
    lateinit var userService: UserService
    lateinit var battleService: BattleService
    lateinit var userIntent: User
    private lateinit var mMap: GoogleMap
    private lateinit var client: FusedLocationProviderClient
    private lateinit var battleIdGlobal: String;
    private var longitude:Double = 0.0;
    private var latitude:Double = 0.0;
    private var battle = Battle();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duel)
        userService = UserService()
        battleService = BattleService()

        val oponent:User = intent.getSerializableExtra("user") as User;
        userService.getUser {
                user ->
            userIntent = user
            battle.setMyName(user.username)
            battle.setOponentName(oponent.username)
            battle.setGoalsPro(0)
            battle.setGoalsAgainst(0)
            battleService.createBattle(battle){
                    battleId ->
                battleIdGlobal = battleId
            }
        }
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

        this.client = LocationServices.getFusedLocationProviderClient(this)

        onClickHome()
        onClickHistory()
        getNameUser()


    }

    override fun onResume() {
        super.onResume()

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this,"deu muito erro",Toast.LENGTH_SHORT)
            return
        }

        println("AQUI BANDO DE ARROMBADO3")
        this.client.lastLocation.addOnSuccessListener { location ->

            println("AQUI BANDO DE ARROMBADO2" + location.latitude)
            if (location !== null) {
                println("AQUI BANDO DE ARROMBADO")
                println(location.latitude)
                println(location.longitude)
                longitude = location.longitude
                latitude = location.latitude

                Toast.makeText(this," " + longitude + " " + latitude,Toast.LENGTH_SHORT)
            }
        }.addOnFailureListener { error ->
            Toast.makeText(this,"Erro com a localização!",Toast.LENGTH_SHORT)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun scoreGoal(view:View){
        var goal = txtGoals.text.toString();
        txtGoals.setText((goal.toInt() + 1).toString())
        battleService.IscoreGoal(battle,battleIdGlobal){
            goalsPro ->
            battle.setGoalsPro(goalsPro)
        }
    }

    fun scoreOponentGoal(view:View){
        var goal = txtAgainstGoals.text.toString();
        txtAgainstGoals.setText((goal.toInt() + 1).toString())
        battleService.oponentScoreGoal(battle,battleIdGlobal){
                goalsAgainst ->
            battle.setGoalsAgainst(goalsAgainst)
        }
    }

    fun doneDuel(view:View){
        battleService.doneDuel(battle,battleIdGlobal){
            result ->
            Toast.makeText(this, "Duelo Encerrado com sucesso",Toast.LENGTH_SHORT).show()
            val intentHome = Intent(this,MainActivity::class.java)
            startActivity(intentHome)
        }

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