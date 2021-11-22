
package com.example.finalplayground.domain.usecases

import com.example.finalplayground.domain.model.ApiResponse
import com.example.finalplayground.domain.model.Movie
import com.example.finalplayground.domain.model.MovieDetail
import com.example.finalplayground.domain.respository.AppRepository
import javax.inject.Inject

/**
 * Usecase layer responsible for fetching data from the repository layer.
 *
 * @param repository DI injected repository
 */
class MovieUseCase @Inject constructor(private val repository: AppRepository) {
    /**
     * Suspend function which fetches the [Movie]
     * @return [Movie]
     */
    suspend fun latest() = repository.latest()

    /**
     * Suspend function which fetches the now playing [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun nowPlaying() = repository.nowPlaying()

    /**
     * Suspend function which fetches the popular movies [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun popular() = repository.popular()

    /**
     * Suspend function which fetches the top rated movies [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun topRated() = repository.topRated()

    /**
     * Suspend function which fetches the upcoming movies [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun upcoming() = repository.upcoming()

    /**
     * Suspend function which fetches the configuration for images [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun configuration() = repository.configuration()

    /**
     * Suspend function which fetches the movies details [MovieDetail]
     * @param id movie id for which details needs to be fetched.
     * @return [movieDetail]
     */
    suspend fun movieDetail(id: Int) = repository.movieDetail(id)
}
