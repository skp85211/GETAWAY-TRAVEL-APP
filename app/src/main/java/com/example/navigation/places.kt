package com.example.navigation

data class place(
    val image:String,
    val id:Int,
    val name:String,
    val description:String,
    val duration:Int,
    var isSelected: Boolean = false
    )