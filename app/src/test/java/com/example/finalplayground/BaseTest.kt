package com.example.finalplayground

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.finalplayground.data.AppRepositoryImpl
import com.example.finalplayground.data.network.MovieApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
// import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

abstract class BaseTest {

    var mockWebServer = MockWebServer()

    @ExperimentalSerializationApi
    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("").toString())
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()
        .create(MovieApi::class.java)

    @ExperimentalSerializationApi
    var repository = AppRepositoryImpl(api)

    fun setResponse(fileName: String) {
        val input = this.javaClass.classLoader?.getResourceAsStream(fileName)
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(input?.bufferedReader().use { it!!.readText() })
        )
    }

    fun setEmptyResponse() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody("[]"))
    }

    fun setErrorResponse() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody("{}"))
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): List<T?> {
    var data: MutableList<T?> = mutableListOf()
    val latch = CountDownLatch(2)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data.add(o)
            latch.countDown()
            if (latch.count == 0L) {
                this@getOrAwaitValue.removeObserver(this)
            }
        }
    }
    this.observeForever(observer)
    afterObserve.invoke()
    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        this.removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }
    @Suppress("UNCHECKED_CAST")
    return data.toList()
}
