package com.example.finalplayground.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.domain.model.ApiResponse
import com.example.finalplayground.domain.model.Configuration
import com.example.finalplayground.domain.model.Movie
import com.example.finalplayground.domain.usecases.MovieUseCase
import com.example.finalplayground.getOrAwaitValue
import com.example.finalplayground.ui.model.LatestMoviesContentHolder
import com.example.finalplayground.ui.model.ListOfMoviesContentHolder
import com.example.finalplayground.ui.viewmodels.MovieListViewModel
import com.example.finalplayground.ui.viewmodels.MovieViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MovieListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var movieUseCase: MovieUseCase

    private lateinit var movieListViewModel: MovieListViewModel

    var latest = readJson<Movie>("latest.json") // Reading mock json
    var nowPlaying = readJson<ApiResponse>("nowPlaying.json") // Reading mock json
    var popular = readJson<ApiResponse>("popular.json") // Reading mock json
    var topRated = readJson<ApiResponse>("topRated.json") // Reading mock json
    var upcoming = readJson<ApiResponse>("upcoming.json") // Reading mock json
    var configuration = readJson<Configuration>("configuration.json") // Reading mock json

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun testMovieViewModel() {
        runBlocking {
            coEvery { movieUseCase.configuration() } returns Resource.success(configuration!!)
            coEvery { movieUseCase.latest() } returns Resource.success(latest!!)
            coEvery { movieUseCase.nowPlaying() } returns Resource.success(nowPlaying!!)
            coEvery { movieUseCase.popular() } returns Resource.success(popular!!)
            coEvery { movieUseCase.topRated() } returns Resource.success(topRated!!)
            coEvery { movieUseCase.upcoming() } returns Resource.success(upcoming!!)
            movieListViewModel = MovieListViewModel(movieUseCase)
            val data = movieListViewModel.remoteMovies.getOrAwaitValue(time = 10)
            assertTrue(data.contains(MovieViewState.Loading))
            assertTrue(data.filterIsInstance(MovieViewState.MovieState::class.java).size == 1)

            val movieState = data.filterIsInstance(MovieViewState.MovieState::class.java).firstOrNull()

            movieState?.run {
                assertTrue(this@MovieListViewModelTest.latest == (latestMovies as LatestMoviesContentHolder).movie)
                assertTrue(this@MovieListViewModelTest.nowPlaying == (nowPlayingMovies as ListOfMoviesContentHolder).response)
                assertTrue(this@MovieListViewModelTest.topRated == (topRatedMovies as ListOfMoviesContentHolder).response)
                assertTrue(this@MovieListViewModelTest.popular == (popularMovies as ListOfMoviesContentHolder).response)
                assertTrue(this@MovieListViewModelTest.upcoming == (upcomingMovies as ListOfMoviesContentHolder).response)
            }
        }
    }

    private inline fun <reified T> readJson(fileName: String): T? {
        val input = this::class.java.classLoader?.getResourceAsStream(fileName) ?: return null
        return Json { ignoreUnknownKeys = true }.decodeFromStream(input)
    }
}
