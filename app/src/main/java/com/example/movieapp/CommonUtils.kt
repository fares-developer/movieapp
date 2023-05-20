package com.example.movieapp

import com.example.movieapp.data.local.entities.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

sealed interface MovieUiState {
    object Success : MovieUiState
    object Loading : MovieUiState
    object Error : MovieUiState
}

enum class MovieTypes(val lower: String) {
    UPCOMING(lower = "upcoming"),
    TOPRATED(lower = "toprated"),
    POPULAR(lower = "popular")
}

fun MovieEntity.getCredits(): List<MovieEntity> {
    return Gson().fromJson(credits, object : TypeToken<List<MovieEntity>>() {}.type)
}

fun MovieEntity.setCredits(credit: List<MovieEntity>) {
    credits = Gson().toJson(credits)
}

/*
fun ItemUiState.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

fun Item.toItemUiState(actionEnabled: Boolean = false): ItemUiState = ItemUiState(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString(),
    actionEnabled = actionEnabled
)

fun ItemUiState.isValid() : Boolean {
    return name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
}*/
