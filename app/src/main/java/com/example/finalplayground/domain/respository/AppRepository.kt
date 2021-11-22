package com.example.finalplayground.domain.respository

import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.domain.model.ApiResponse
import com.example.finalplayground.domain.model.Configuration
import com.example.finalplayground.domain.model.Movie
import com.example.finalplayground.domain.model.MovieDetail

/**
 * Repository layer for fetching data from either network or db layer.
 */
interface AppRepository {
    /**
     * Suspend function which fetches the [Movie]
     * @return [Movie]
     */
    suspend fun latest(): Resource<Movie>

    /**
     * Suspend function which fetches the now playing [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun nowPlaying(): Resource<ApiResponse>

    /**
     * Suspend function which fetches the popular movies [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun popular(): Resource<ApiResponse>

    /**
     * Suspend function which fetches the top rated movies [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun topRated(): Resource<ApiResponse>

    /**
     * Suspend function which fetches the upcoming movies [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun upcoming(): Resource<ApiResponse>

    /**
     * Suspend function which fetches the configuration for images [ApiResponse]
     * @return [ApiResponse]
     */
    suspend fun configuration(): Resource<Configuration>

    /**
     * Suspend function which fetches the movies details [MovieDetail]
     * @param id movie id for which details needs to be fetched.
     * @return [movieDetail]
     */
    suspend fun movieDetail(id: Int): Resource<MovieDetail>
}
