package com.example.movieapp

import com.example.movieapp.data.remote.MovieRepoImplements
import com.example.movieapp.fake.FakeDataSource
import com.example.movieapp.fake.FakeMovieApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteMovieRepoTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun remoteMarsPhotosRepository_getUpcoming_verifyPhotoList() = runTest {
        val repo = MovieRepoImplements(movieRepo = FakeMovieApiService())
        assertEquals(repo.getUpcomingMovies(), FakeDataSource.upcomingList)
    }
}