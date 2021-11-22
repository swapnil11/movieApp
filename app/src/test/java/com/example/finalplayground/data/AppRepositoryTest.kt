package com.example.finalplayground.data

import com.example.finalplayground.BaseTest
import com.example.finalplayground.data.network.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class AppRepositoryTest : BaseTest() {

    @Test
    fun testSuccessResponse() {
        setResponse("popular.json")
        runBlocking {
            Assert.assertTrue(
                repository.popular().status == Resource.Status.SUCCESS
            )
        }
    }

    @Test
    fun testFailResponse() {
        setErrorResponse()
        runBlocking {
            Assert.assertTrue(
                repository.popular().status == Resource.Status.ERROR
            )
        }
    }

    @Test
    fun testMovieItems() {
        setResponse("popular.json")
        runBlocking {
            val expectedItems = 20
            Assert.assertEquals(
                repository.popular().data?.results?.size, expectedItems
            )
        }
    }

    @Test
    fun testEmptyResponse() {
        setEmptyResponse()
        runBlocking {
            val response = repository.popular()
            Assert.assertTrue(
                response.status == Resource.Status.ERROR
            )
            Assert.assertEquals(
                response.data?.results?.size, null
            )
        }
    }
}
