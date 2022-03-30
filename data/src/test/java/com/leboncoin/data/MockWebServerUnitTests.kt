package com.leboncoin.data

import android.content.Context
import com.google.gson.Gson
import com.leboncoin.data.api.EndPoint
import com.leboncoin.data.api.LeBonCoinApi
import com.leboncoin.data.db.datasource.AlbumsDataSource
import com.leboncoin.data.mapper.AlbumMapper
import com.leboncoin.data.repositoryimpl.AlbumRepositoryImp
import com.leboncoin.data.response.MockResponseFileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit


@RunWith(MockitoJUnitRunner::class)
class MockWebServerUnitTests {

    private val server = MockWebServer()
    private lateinit var api: LeBonCoinApi
    private lateinit var jsonRepository: AlbumRepositoryImp
    private var albumMapper = AlbumMapper()
    private lateinit var albumsDataSource: AlbumsDataSource

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun init() {

        albumsDataSource = AlbumsDataSource(mockContext)
        server.start(8000)

        api = LeBonCoinApi(
            Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(EndPoint::class.java)
        )

        jsonRepository = AlbumRepositoryImp(api, albumsDataSource, albumMapper)
    }

    @After
    fun shutdown() {
        server.shutdown()
    }

    @Test
    fun `test APIs parse correctly`() {
        server.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("response.json").content).setResponseCode(
                HttpURLConnection.HTTP_OK))
        }
        jsonRepository.getAlbums()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .assertValueCount(1)
            .assertValue { it.size == 2 }
            .assertNoErrors()
    }

    @Test
    fun `test server not found error`() {
        server.apply() {
            enqueue(MockResponse().setBody("").setResponseCode(HttpURLConnection.HTTP_NOT_FOUND))
        }
        jsonRepository.getAlbums()
            .test()
            .assertError(HttpException::class.java)
    }

    @Test
    fun `test internal server error`() {
        server.apply() {
            enqueue(
                MockResponse().setBody(MockResponseFileReader("response.json").content)
                    .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
            )
        }
        jsonRepository.getAlbums()
            .test()
            .assertError(HttpException::class.java)
    }
}