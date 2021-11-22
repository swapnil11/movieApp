package com.example.finalplayground.data.network

import com.example.finalplayground.domain.model.ApiResponse
import com.example.finalplayground.domain.model.Configuration
import com.example.finalplayground.domain.model.Movie
import com.example.finalplayground.domain.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API class for defining endpoint and request for the application
 */
interface MovieApi {
    /**
     * Suspend function which fetches the [Movie] from the [LATEST_ENDPOINT].
     * @return [Movie]
     */
    @GET(LATEST_ENDPOINT)
    suspend fun latest(): Movie

    /**
     * Suspend function which fetches the now playing [ApiResponse] from the [NOW_PLAYING_ENDPOINT].
     * @return [ApiResponse]
     */
    @GET(NOW_PLAYING_ENDPOINT)
    suspend fun nowPlaying(): ApiResponse

    /**
     * Suspend function which fetches the popular movies [ApiResponse] from the [POPULAR_ENDPOINT].
     * @return [ApiResponse]
     */
    @GET(POPULAR_ENDPOINT)
    suspend fun popular(): ApiResponse

    /**
     * Suspend function which fetches the top rated movies [ApiResponse] from the [TOP_RATED_ENDPOINT].
     * @return [ApiResponse]
     */
    @GET(TOP_RATED_ENDPOINT)
    suspend fun topRated(): ApiResponse

    /**
     * Suspend function which fetches the upcoming movies [ApiResponse] from the [UPCOMING_ENDPOINT].
     * @return [ApiResponse]
     */
    @GET(UPCOMING_ENDPOINT)
    suspend fun upcoming(): ApiResponse

    /**
     * Suspend function which fetches the configuration for images [ApiResponse] from the [CONFIGURATION_ENDPOINT].
     * @return [ApiResponse]
     */
    @GET(CONFIGURATION_ENDPOINT)
    suspend fun configuration(): Configuration

    /**
     * Suspend function which fetches the movies details [MovieDetail] from the [MOVIE_DETAIL_ENDPOINT].
     * @param id movie id for which details needs to be fetched.
     * @return [movieDetail]
     */
    @GET(MOVIE_DETAIL_ENDPOINT)
    suspend fun movieDetail(@Path("id") id: Int): MovieDetail

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val LATEST_ENDPOINT = "movie/latest?api_key=48cf8f2884529507f1512619e9fcd26f&language=en-US"
        private const val NOW_PLAYING_ENDPOINT = "movie/now_playing?api_key=48cf8f2884529507f1512619e9fcd26f&language=en-US"
        private const val POPULAR_ENDPOINT = "movie/popular?api_key=48cf8f2884529507f1512619e9fcd26f&language=en-US"
        private const val TOP_RATED_ENDPOINT = "movie/top_rated?api_key=48cf8f2884529507f1512619e9fcd26f&language=en-US"
        private const val UPCOMING_ENDPOINT = "movie/upcoming?api_key=48cf8f2884529507f1512619e9fcd26f&language=en-US"
        private const val CONFIGURATION_ENDPOINT = "configuration?api_key=48cf8f2884529507f1512619e9fcd26f"
        private const val MOVIE_DETAIL_ENDPOINT = "movie/{id}?api_key=48cf8f2884529507f1512619e9fcd26f"
    }
}
