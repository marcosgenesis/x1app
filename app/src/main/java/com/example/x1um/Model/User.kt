package com.example.x1um.Model

import java.io.Serializable

class User (Id: String, Name: String, Username: String, Email: String):Serializable {
    var id = Id
    var name = Name
    var username = Username
    var email = Email

    class UserBuilder private constructor(){
        private var id = "";
        private var name = "";
        private var username = "";
        private var email = "";

        fun setId(id: String): User.UserBuilder {
            this.id = id;
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

        fun build():User{
            val user = User(id,name,username,email);
            return user;
        }

        companion object {
            fun builder(): User.UserBuilder {
                return User.UserBuilder()
            }
        }
    }

}