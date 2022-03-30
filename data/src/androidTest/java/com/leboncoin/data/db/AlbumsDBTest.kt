package com.leboncoin.data.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.leboncoin.data.db.dao.AlbumDao
import com.leboncoin.data.mapper.AlbumMapper
import com.leboncoin.domain.models.Album
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AlbumsDBTest : TestCase(){

    private lateinit var albumDao: AlbumDao
    private lateinit var db: AlbumsDB
    private val albumMapper =AlbumMapper()

    @Before
    public override fun setUp() {
        val context =  InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, AlbumsDB::class.java
        ).build()
        albumDao = db.AlbumDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadAlbum()  {
        val album = Album(1, 100, "album title","url_test","thumb_url")
        albumDao.insert(albumMapper.toAlbumResp(album))
        val albums = albumMapper.toAlbums(albumDao.getAll())
        assert(albums.contains(album))
    }
}


