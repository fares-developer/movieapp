package com.example.movieapp

import com.example.movieapp.fake.FakeDataSource
import com.example.movieapp.fake.FakeRemoteMovieRepo
import com.example.movieapp.rules.TestDispatcherRule
import com.example.movieapp.ui.HomeViewModel
import com.example.movieapp.ui.MovieUiState
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
    fun homeViewModel_getUpcoming_verifyUiStateSuccess() = runTest {
        val homeVM = HomeViewModel(
            movieRepo = FakeRemoteMovieRepo()
        )

        assertEquals(
            MovieUiState.Success(
                "El total de elementos es ${FakeDataSource.upcomingList.results.size}"
            ),
            homeVM.cinemaUiState
        )
    }
}