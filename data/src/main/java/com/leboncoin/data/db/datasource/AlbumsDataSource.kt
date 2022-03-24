package com.leboncoin.data.db.datasource

import com.leboncoin.data.db.AlbumsDB
import com.leboncoin.data.response.AlbumResp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AlbumsDataSource @Inject constructor (@ApplicationContext val context: android.content.Context) {
    private val dataBase= AlbumsDB.invoke(context)

    fun insertAlbum(albumResp: AlbumResp)  {
        return dataBase.AlbumDao().insert(albumResp)
    }

    fun getAlbums(): List<AlbumResp> {
        return  dataBase.AlbumDao().getAll()
    }

    fun getAlbumsCount():Int{
        return dataBase.AlbumDao().getAlbumsCount()
    }

}