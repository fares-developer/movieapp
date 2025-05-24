package com.example.movieapp

import com.example.movieapp.fake.FakeDataSource
import com.example.movieapp.fake.FakeRemoteMovieRepo
import com.example.movieapp.rules.TestDispatcherRule
import com.example.movieapp.ui.HomeViewModel
import com.example.movieapp.ui.MovieUiState // Ensure this import is present
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun homeViewModel_getMovies_verifyUiStateIsSuccess() { // Changed = to {
        runTest { // Enclosed in braces
            val homeVM = HomeViewModel(
                movieRepo = FakeRemoteMovieRepo()
            )
            // getMovies is called in init, runTest will complete its coroutine

            // Assert that the UI state is the Success object
            assertEquals(MovieUiState.Success, homeVM.cinemaUiState)

            // Optionally, assert the contents of listMovies separately
            val expectedNowPlaying = FakeDataSource.upcomingList
            // ... and so on for other categories
            assertEquals(expectedNowPlaying, homeVM.listMovies[0])
            // ...
        }
    }
}