package com.example.x1um.Model.Mock

import com.example.x1um.Model.User
import java.util.*


object Users {
    fun fakeUsers(): List<User> {
        return Arrays.asList(
            User.UserBuilder.builder().setId("123").setName("Marcos Nascimento").setEmail("marquin@nascimento.com").setUsername("marquin").build(),
            User.UserBuilder.builder().setId("1234").setName("Marcos Nogueira").setEmail("marquin@nascimento.com").setUsername("italo").build(),
            User.UserBuilder.builder().setId("1235").setName("Marcos Mito").setEmail("marquin@nascimento.com").setUsername("nascimento").build(),
            User.UserBuilder.builder().setId("1236").setName("Marcos Pedra branca").setEmail("marquin@nascimento.com").setUsername("gusta").build(),
            User.UserBuilder.builder().setId("1237").setName("Marquinhos de morada nova").setEmail("marquin@nascimento.com").setUsername("jefo").build(),
            User.UserBuilder.builder().setId("1238").setName("Marquin rei delas").setEmail("marquin@nascimento.com").setUsername("ericALenda").build(),

            )
    }

    fun fakeRankingUsers(): List<User> {
        return Arrays.asList(
            User.UserBuilder.builder().setId("1234").setName("Ítalo Lima").setEmail("italolima@gmail.com").setUsername("italo").build(),
            User.UserBuilder.builder().setId("12344").setName("Ítalo Mito").setEmail("italo@nascimento.com").setUsername("italo2").build(),
            User.UserBuilder.builder().setId("12354").setName("Ítalo Rei").setEmail("italo@nascimento.com").setUsername("italo3").build(),
            User.UserBuilder.builder().setId("12364").setName("Ítalo Pedra branca").setEmail("italo@nascimento.com").setUsername("italo4").build(),
            User.UserBuilder.builder().setId("12374").setName("Ítalo de morada nova").setEmail("italo@nascimento.com").setUsername("italo5").build(),
            User.UserBuilder.builder().setId("12384").setName("Ítalo rei delas").setEmail("italo@nascimento.com").setUsername("italos6").build(),

            )
    }

}