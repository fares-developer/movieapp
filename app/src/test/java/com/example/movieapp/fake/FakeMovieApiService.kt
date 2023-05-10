package com.example.movieapp.fake

import com.example.movieapp.data.remote.MovieApiService

class FakeMovieApiService: MovieApiService {
    override suspend fun getUpcomingMovies(api_key: String): Results {
        return FakeDataSource.upcomingList
    }

    override suspend fun getTopRatedMovies(api_key: String): Results {
        return FakeDataSource.topratedList
    }

    override suspend fun getPopularMovies(api_key: String): Results {
        return FakeDataSource.popularList
    }


}