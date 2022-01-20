package com.example.x1um.Model

class Battle {
    private var myName: String? = null
    private var oponentName: String? = null
    private var goalsPro = 0
    private var goalsAgainst = 0
    private var finished = false
    private var winner: String? = null

    fun getMyName(): String? {
        return myName
    }

    fun setMyName(myName: String?) {
        this.myName = myName
    }

    fun getOponentName(): String? {
        return oponentName
    }

    fun setOponentName(oponentName: String?) {
        this.oponentName = oponentName
    }

    fun getGoalsPro(): Int {
        return goalsPro
    }

    fun setGoalsPro(goalsPro: Int) {
        this.goalsPro = goalsPro
    }

    fun getGoalsAgainst(): Int {
        return goalsAgainst
    }

    fun setGoalsAgainst(goalsAgainst: Int) {
        this.goalsAgainst = goalsAgainst
    }

    fun isFinished(): Boolean {
        return finished
    }

    fun setFinished(finished: Boolean) {
        this.finished = finished
    }

    fun getWinner(): String? {
        return winner
    }

    fun setWinner(winner: String?) {
        this.winner = winner
    }

    class BattleBuilder private constructor() {
        private var myName: String? = null
        private var oponentName: String? = null
        private var goalsPro = 0
        private var goalsAgainst = 0
        private var finished = false
        private var winner: String? = null
        fun setMyName(myName: String?): BattleBuilder {
            this.myName = myName
            return this
        }

        fun setOponentName(oponentName: String?): BattleBuilder {
            this.oponentName = oponentName
            return this
        }

        fun setGoalsPro(goalsPro: Int): BattleBuilder {
            this.goalsPro = goalsPro
            return this
        }

        fun setGoalsAgainst(goalsAgainst: Int): BattleBuilder {
            this.goalsAgainst = goalsAgainst
            return this
        }

        fun setFinished(finished: Boolean): BattleBuilder {
            this.finished = finished
            return this
        }

        fun setWinner(winner: String?): BattleBuilder {
            this.winner = winner
            return this
        }

        fun build(): Battle {
            val battle = Battle()
            battle.myName = myName
            battle.oponentName = oponentName
            battle.goalsPro = goalsPro
            battle.goalsAgainst = goalsAgainst
            battle.winner = winner
            battle.finished = finished
            return battle
        }

        companion object {
            fun builder(): BattleBuilder {
                return BattleBuilder()
            }
        }
    }
}
