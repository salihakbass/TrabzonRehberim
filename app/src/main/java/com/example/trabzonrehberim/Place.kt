package com.example.trabzonrehberim

import java.io.Serializable

data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val image: String
) : Serializable
