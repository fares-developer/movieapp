package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import com.example.movieapp.ui.state.StartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartViewModel : ViewModel() {

    private var _startState = MutableStateFlow(StartState())
    val startState = _startState.asStateFlow()

    fun changeSelectedNavItem(index: Int) {
        _startState.value.selectedNavItem = index
    }

    fun showBars(showAppBar: Boolean, showNav: Boolean) {
        _startState.value.apply {
            topBar = showAppBar
            navBar = showNav
        }
    }

}