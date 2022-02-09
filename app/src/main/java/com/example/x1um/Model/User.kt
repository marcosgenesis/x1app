package com.example.x1um.Model

import java.io.Serializable

class User (Id: String, Name: String, Username: String, Email: String, Points: Int, Games: Int, Position: Int):Serializable {
    var id = Id
    var name = Name
    var username = Username
    var email = Email
    var points = Points
    var games = Games
    var position = Position

    class UserBuilder private constructor(){
        private var id = ""
        private var name = ""
        private var username = ""
        private var email = ""
        private var points = 0
        private var games = 0
        private var position = 0

        fun setId(id: String): User.UserBuilder {
            this.id = id
            return this
        }

        fun setName(name: String): User.UserBuilder {
            this.name = name;
            return this
        }

        fun setUsername(username: String): User.UserBuilder {
            this.username = username;
            return this
        }

        fun setEmail(email: String): User.UserBuilder {
            this.email = email;
            return this
        }

        fun setPoints(points: Int): User.UserBuilder {
            this.points = points
            return this
        }

        fun setGames(games: Int): User.UserBuilder {
            this.games = games
            return this
        }

        fun setPosition(position: Int): User.UserBuilder {
            this.position = position
            return this
        }

        fun build():User{
            val user = User(id,name,username,email, points, games, position)
            return user
        }

        companion object {
            fun builder(): User.UserBuilder {
                return User.UserBuilder()
            }
        }
    }

}