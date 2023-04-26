package com.example.movieapp.data.state

data class StartState(
    var topBar: Boolean = false,
    var navBar: Boolean = false,
    var selectedNavItem: Int = 0
)