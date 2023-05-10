package com.example.movieapp.ui.state

data class StartState(
    var topBar: Boolean = false,
    var navBar: Boolean = false,
    var selectedNavItem: Int = 0
)