package com.leboncoin.data.mapper


import com.leboncoin.data.response.AlbumResp
import com.leboncoin.domain.models.Album
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumMapperTest {

    private var albumMapper = AlbumMapper()
    private val albumResp =AlbumResp(1,1,"title","url","thumbnailUrl")
    private val album = Album(1,1,"title","url","thumbnailUrl")
    private val albumRespList = listOf(albumResp)


    @Test
    fun `to Album Model`() {
        val result=albumMapper.toAlbumModel(albumRespList)
        assertEquals(result.size,albumRespList.size)
    }

    @Test
    fun `to Albums`() {
        val result=albumMapper.toAlbums(albumRespList)
        assertEquals(result.size,albumRespList.size)
    }

    @Test
    fun `to Album`() {
        val result =albumMapper.toAlbum(albumResp)
        assertEquals(result.albumId,albumResp.albumId)
        assertEquals(result.id,albumResp.id)
        assertEquals(result.url,albumResp.url)
        assertEquals(result.thumbnailUrl,albumResp.thumbnailUrl)
    }

    @Test
    fun `to AlbumResp`() {
        val result =albumMapper.toAlbumResp(album)
        assertEquals(album.albumId,result.albumId)
        assertEquals(album.id,result.id)
        assertEquals(album.url,result.url)
        assertEquals(album.thumbnailUrl,result.thumbnailUrl)
    }
}