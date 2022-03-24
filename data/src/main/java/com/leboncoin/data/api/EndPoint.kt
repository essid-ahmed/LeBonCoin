package com.leboncoin.data.api

import com.leboncoin.data.response.AlbumResp
import io.reactivex.Single
import retrofit2.http.GET

interface EndPoint {
    @GET("/img/shared/technical-test.json")
    fun getAlbums(): Single<List<AlbumResp>>

}