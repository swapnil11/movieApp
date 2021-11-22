package com.example.finalplayground.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.finalplayground.domain.usecases.MovieUseCase
import com.example.finalplayground.ui.model.LatestMoviesContentHolder
import com.example.finalplayground.ui.model.ListOfMoviesContentHolder
import com.example.finalplayground.ui.model.MovieContentHolder
import com.example.finalplayground.ui.model.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    private val remoteMoviesLiveData = MutableLiveData(Unit)

    val remoteMovies = remoteMoviesLiveData.switchMap {
        fetchMovies()
    }

    private fun fetchMovies() = liveData {
        emit(MovieViewState.Loading)
        emit(fetchMoviesAsync())
    }

    private suspend fun fetchMoviesAsync(): MovieViewState {
        return coroutineScope {

            val configuration = useCase.configuration().data
            val latestMoviesDeferred = async { useCase.latest() }
            val nowPlayingDeferred = async { useCase.nowPlaying() }
            val popularDeferred = async { useCase.popular() }
            val topRatedDeferred = async { useCase.topRated() }
            val upcomingDeferred = async { useCase.upcoming() }

            if (configuration != null) {

                return@coroutineScope MovieViewState.MovieState(
                    LatestMoviesContentHolder(
                        latestMoviesDeferred.await().data,
                        configuration
                    ),
                    ListOfMoviesContentHolder(
                        nowPlayingDeferred.await().data,
                        configuration,
                        MovieType.NOW_PLAYING_MOVIE_TYPE
                    ),
                    ListOfMoviesContentHolder(
                        topRatedDeferred.await().data,
                        configuration,
                        MovieType.TOP_RATED_MOVIE_TYPE
                    ),
                    ListOfMoviesContentHolder(
                        popularDeferred.await().data,
                        configuration,
                        MovieType.POPULAR_MOVIE_TYPE
                    ),
                    ListOfMoviesContentHolder(
                        upcomingDeferred.await().data,
                        configuration,
                        MovieType.UPCOMING_MOVIE_TYPE
                    )
                )
            } else {
                MovieViewState.LoadingFinished
            }
        }
    }
}

sealed class MovieViewState {
    object Loading : MovieViewState()
    data class MovieState(
        val latestMovies: MovieContentHolder,
        val nowPlayingMovies: MovieContentHolder,
        val topRatedMovies: MovieContentHolder,
        val popularMovies: MovieContentHolder,
        val upcomingMovies: MovieContentHolder
    ) : MovieViewState()

    object LoadingFinished : MovieViewState()
}
