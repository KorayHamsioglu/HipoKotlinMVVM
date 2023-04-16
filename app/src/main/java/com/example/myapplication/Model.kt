package com.example.myapplication

data class Member(
    val name: String,
    val age: Int,
    val location: String,
    val github: String,
    val hipo: Hipo
)

data class Hipo(
    val position: String,
    val years_in_hipo: Int
)

data class Team(
    val company: String,
    val team: String,
    val members: List<Member>
)
