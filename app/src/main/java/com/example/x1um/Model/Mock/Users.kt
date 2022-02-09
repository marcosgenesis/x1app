package com.example.x1um.Model.Mock

import com.example.x1um.Model.User
import java.util.*


object Users {
    fun fakeUsers(): List<User> {
        return Arrays.asList(
            User.UserBuilder.builder().setId("123").setName("Marcus").setEmail("marquin@nascimento.com").setUsername("marquin").build(),
            User.UserBuilder.builder().setId("1234").setName("Eric Rodrigues").setEmail("marquin@nascimento.com").setUsername("italo").build(),
            User.UserBuilder.builder().setId("1235").setName("Lucas Nascimento").setEmail("marquin@nascimento.com").setUsername("nascimento").build(),
            User.UserBuilder.builder().setId("1236").setName("Gustabo").setEmail("marquin@nascimento.com").setUsername("gusta").build(),
            User.UserBuilder.builder().setId("1237").setName("Jefo de morada nova").setEmail("marquin@nascimento.com").setUsername("jefo").build(),
            User.UserBuilder.builder().setId("1238").setName("Eric rei delas").setEmail("marquin@nascimento.com").setUsername("ericALenda").build(),

            )
    }

    fun fakeRankingUsers(): List<User> {
        return Arrays.asList(
            User.UserBuilder.builder().setId("123").setName("Marcus").setEmail("marquin@nascimento.com").setUsername("marquin").build(),
            User.UserBuilder.builder().setId("1234").setName("Eric Rodrigues").setEmail("marquin@nascimento.com").setUsername("italo").build(),
            User.UserBuilder.builder().setId("1235").setName("Lucas Nascimento").setEmail("marquin@nascimento.com").setUsername("nascimento").build(),
            User.UserBuilder.builder().setId("1236").setName("Gustabo").setEmail("marquin@nascimento.com").setUsername("gusta").build(),
            User.UserBuilder.builder().setId("1237").setName("Jefo de morada nova").setEmail("marquin@nascimento.com").setUsername("jefo").build(),
            User.UserBuilder.builder().setId("1238").setName("Eric rei delas").setEmail("marquin@nascimento.com").setUsername("ericALenda").build(),

            )
    }

}