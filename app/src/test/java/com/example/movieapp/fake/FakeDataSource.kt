package com.example.movieapp.fake

import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.model.Results

object FakeDataSource {

    val upcomingList:Results = Results(
        results = listOf(
            MovieModel(id = 0, title = "La Venganza de Salazar"),
            MovieModel(id = 1, title = "Las Lluvias de Castamir")
        )
    )

    val topratedList:Results = Results(
        results = listOf(
            MovieModel(id = 0, title = "Mario Bros"),
            MovieModel(id = 1, title = "El Gato con Botas")
        )
    )

    val popularList:Results = Results(
        results = listOf(
            MovieModel(id = 0, title = "Mario Bros"),
            MovieModel(id = 1, title = "Fast X")
        )
    )
}