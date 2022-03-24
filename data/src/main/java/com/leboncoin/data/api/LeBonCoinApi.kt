package com.leboncoin.data.api

import com.leboncoin.data.response.AlbumResp
import io.reactivex.Single
import javax.inject.Inject

class LeBonCoinApi @Inject constructor (private val endPoint: EndPoint){
    fun getAlbums():Single<List<AlbumResp>>
    {
        return endPoint.getAlbums()
    }
}