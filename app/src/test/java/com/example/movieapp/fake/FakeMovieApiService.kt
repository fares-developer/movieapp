package com.example.movieapp.fake

import com.example.movieapp.data.model.Credits
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.model.Results
import com.example.movieapp.data.remote.MovieApiService

class FakeMovieApiService: MovieApiService {
    override suspend fun getNowPlayingMovies(api_key: String): Results {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcomingMovies(api_key: String): Results {
        return FakeDataSource.upcomingList
    }

    override suspend fun getTopRatedMovies(api_key: String): Results {
        return FakeDataSource.topratedList
    }

    override suspend fun getPopularMovies(api_key: String): Results {
        return FakeDataSource.popularList
    }

    override suspend fun getDetailsMovie(
        idMovie: String,
        api_key: String
    ): MovieModel {
        TODO("Not yet implemented")
    }

    override suspend fun getCredits(
        idMovie: String,
        api_key: String
    ): Credits {
        TODO("Not yet implemented")
    }


}