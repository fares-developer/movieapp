package com.example.movieapp.data.model

data class Results(
    val dates: Dates = Dates("",""),
    val page: Int = 1,
    val results: List<MovieModel>,
    val total_pages: Int = 1,
    val total_results: Int = 1
)