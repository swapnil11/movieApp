package com.example.finalplayground.data

import com.example.finalplayground.data.network.MovieApi
import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.data.network.ResponseHandler
import com.example.finalplayground.domain.model.ApiResponse
import com.example.finalplayground.domain.model.Configuration
import com.example.finalplayground.domain.model.Movie
import com.example.finalplayground.domain.model.MovieDetail
import com.example.finalplayground.domain.respository.AppRepository
import javax.inject.Inject

/**
 * Repository layer for fetching data from either network or db layer.
 */
class AppRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : AppRepository {

    /**
     * Fetches the latest movies from the remote API.
     */
    override suspend fun latest(): Resource<Movie> = try {
        ResponseHandler.handleSuccess(api.latest())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }

    override suspend fun nowPlaying(): Resource<ApiResponse> = try {
        ResponseHandler.handleSuccess(api.nowPlaying())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }

    override suspend fun popular(): Resource<ApiResponse> = try {
        ResponseHandler.handleSuccess(api.popular())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }

    override suspend fun topRated(): Resource<ApiResponse> = try {
        ResponseHandler.handleSuccess(api.topRated())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }

    override suspend fun upcoming(): Resource<ApiResponse> = try {
        ResponseHandler.handleSuccess(api.upcoming())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }

    override suspend fun configuration(): Resource<Configuration> = try {
        ResponseHandler.handleSuccess(api.configuration())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }

    override suspend fun movieDetail(id: Int): Resource<MovieDetail> = try {
        ResponseHandler.handleSuccess(api.movieDetail(id))
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }
}
