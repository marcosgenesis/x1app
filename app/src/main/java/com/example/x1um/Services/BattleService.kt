package com.example.x1um.Services

import com.example.x1um.Model.Battle
import com.example.x1um.Model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BattleService {
    val db = Firebase.firestore
    lateinit var userService: UserService
    var countOfVictorys: Int = 0
    var points: Int = 0
    var countOfVictorysOponent: Int = 0
    var pointsOponent: Int = 0
    lateinit var userGlobal: User
    lateinit var userOponent: User

    fun listBattles(resultSuccessful: (battles: ArrayList<Battle>, points: Int, games: Int, user:User) -> Unit) {
        userService = UserService()

       userService.getUser() {
            user ->
                userGlobal = user

           val battlesList: ArrayList<Battle> = ArrayList()

           db.collection("battles").whereEqualTo("myName", userGlobal.name).get()
               .addOnSuccessListener { documentReference ->
                   var userOponent = User("", "", "", "", 0 , 0, 0)

                   for(document in documentReference) {
                       var battle = Battle();
                       battle.setMyName(document.data["myName"] as String?)
                       battle.setOponentName(document.data["oponentName"] as String?)
                       battle.setGoalsPro(document.data["goalsPro"].toString().toInt())
                       battle.setGoalsAgainst(document.data["goalsAgainst"].toString().toInt())
                       battle.setFinished(true)
                       battle.setWinner(document.data["winner"] as String?)
                       if(document.data["winner"] == document.data["myName"]) {
                           countOfVictorys += 1
                           points = countOfVictorys * 3
                       }else if(document.data["winner"] == document.data["oponentName"]) {
                           countOfVictorysOponent += 1
                           pointsOponent = countOfVictorys * 3
                       }

                       userService.getUserByName(document.data["oponentName"] as String) {
                           userReturn ->
                           userOponent = userReturn
                           userOponent.points = pointsOponent
                           userOponent.name = document.data["oponentName"] as String
                           userOponent.username = userReturn.username

                           if(battle.isFinished()) {
                               battlesList.add(battle)
                           }

                           userOponent.games = battlesList.size
                           userGlobal.points = points

                           resultSuccessful(battlesList, points, battlesList.size, userOponent)
                       }
                   }
               }
        }
    }

    fun createBattle(battle:Battle,resultSuccessful: (battleId:String) -> Unit) {
        db.collection("battles").add(battle).addOnSuccessListener {
            battle ->
            var battleId = battle.id
            resultSuccessful(battleId)
        }
    }

    fun IscoreGoal(battle: Battle,battleId:String,resultSuccessful: (goalsPro:Int) -> Unit){
        db.collection("battles").document(battleId).update("goalsPro", battle.getGoalsPro() + 1).addOnSuccessListener {
                docu ->
            resultSuccessful(battle.getGoalsPro() + 1)
        }
    }

    fun oponentScoreGoal(battle: Battle,battleId:String,resultSuccessful: (goalsAgainst:Int) -> Unit){
        db.collection("battles").document(battleId).update("goalsAgainst", battle.getGoalsAgainst() + 1).addOnSuccessListener {
                docu ->
            resultSuccessful(battle.getGoalsAgainst() + 1)
        }
    }

    fun doneDuel(battle: Battle,battleId:String,resultSuccessful: (result:Boolean) -> Unit){
        var winner = if (battle.getGoalsPro() > battle.getGoalsAgainst())  battle.getMyName() else battle.getOponentName()
        if (battle.getGoalsPro() == battle.getGoalsAgainst()) {
            db.collection("battles").document(battleId).update("finished", true).addOnSuccessListener {
                    docu ->
                resultSuccessful(true)
            }
        }else{
            db.collection("battles").document(battleId).update("finished", true, "winner", winner ).addOnSuccessListener {
                    docu ->
                resultSuccessful(true)
            }
        }

    }
}