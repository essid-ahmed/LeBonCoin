package com.leboncoin.domain.repository

import com.leboncoin.domain.models.Album
import io.reactivex.Single

interface IAlbumsRepository {
    fun getAlbums(): Single<List<Album>>
    fun isDbEmpty() : Boolean
}