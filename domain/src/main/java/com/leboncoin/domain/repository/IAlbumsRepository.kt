package com.leboncoin.domain.repository

import com.leboncoin.domain.models.Album
import io.reactivex.Completable
import io.reactivex.Single

interface IAlbumsRepository {
    fun getAlbums(): Single<List<Album>>
    fun putAlbums(albums :List<Album>): Completable
    fun getLocalAlbums() : List<Album>
    fun isDbEmpty() : Boolean
}