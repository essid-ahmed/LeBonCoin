package com.leboncoin.domain.result

import com.leboncoin.domain.models.Album
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class AlbumResultUnitTests {
    @Spy
    private val albums: List<Album> = ArrayList()

    @Test
    fun `test failure`() {
        val error = AlbumResult.Failure(IOException("server error"))
        assert(error is AlbumResult.Failure)
    }

    @Test
    fun `test success`() {
        val success = AlbumResult.Success(albums)
        assert(success is AlbumResult.Success)
    }


    @Test
    fun `test loading`() {
        val loading = AlbumResult.Loading
        assert(loading is AlbumResult.Loading)
    }
}