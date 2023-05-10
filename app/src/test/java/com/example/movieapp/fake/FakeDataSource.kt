package com.example.movieapp.fake

object FakeDataSource {

    val upcomingList:Results = Results(
        results = listOf(
            Movie(id = 0, title = "La Venganza de Salazar"),
            Movie(id = 1, title = "Las Lluvias de Castamir")
        )
    )

    val topratedList:Results = Results(
        results = listOf(
            Movie(id = 0, title = "Mario Bros"),
            Movie(id = 1, title = "El Gato con Botas")
        )
    )

    val popularList:Results = Results(
        results = listOf(
            Movie(id = 0, title = "Mario Bros"),
            Movie(id = 1, title = "Fast X")
        )
    )
}