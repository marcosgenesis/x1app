package com.example.x1um.Model

import java.util.*


object Users {
    fun fakeUsers(): List<User> {
        return Arrays.asList(
            User.UserBuilder.builder().setId("123").setName("Lucas Nascimento").setEmail("lucas@nascimento.com").setUsername("lucas").build(),
            User.UserBuilder.builder().setId("1234").setName("Lucas Nogueira").setEmail("lucas@nascimento.com").setUsername("lucas2").build(),
            User.UserBuilder.builder().setId("1235").setName("Lucas Mito").setEmail("lucas@nascimento.com").setUsername("lucas3").build(),
            User.UserBuilder.builder().setId("1236").setName("Lucas Pedra branca").setEmail("lucas@nascimento.com").setUsername("lucas4").build(),
            User.UserBuilder.builder().setId("1237").setName("Luquinhas de morada nova").setEmail("lucas@nascimento.com").setUsername("lucas5").build(),
            User.UserBuilder.builder().setId("1238").setName("lucas rei delas").setEmail("lucas@nascimento.com").setUsername("lucas6").build(),

            )
    }
}