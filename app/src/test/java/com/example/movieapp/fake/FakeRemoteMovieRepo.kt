package com.example.movieapp.fake

import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.model.Results
import com.example.movieapp.data.remote.MovieRepository

class FakeRemoteMovieRepo: MovieRepository {
    override suspend fun getNowPlayingMovies(): Results {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcomingMovies(): Results {
        return FakeDataSource.upcomingList
    }

    override suspend fun getTopRatedMovies(): Results {
        return FakeDataSource.topratedList
    }

    override suspend fun getPopularMovies(): Results {
        return FakeDataSource.popularList
    }

    override suspend fun getDetailsMovie(idMovie: Int): MovieModel {
        TODO("Not yet implemented")
    }
}