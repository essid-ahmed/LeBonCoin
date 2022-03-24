package com.leboncoin.domain.result

import com.leboncoin.domain.models.Album


sealed class AlbumResult {
    object Loading : AlbumResult()
    data class Success(val albumList : List<Album>): AlbumResult()
    data class Failure(val throwable: Throwable) : AlbumResult()
}