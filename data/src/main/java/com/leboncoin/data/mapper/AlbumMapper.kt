package com.leboncoin.data.mapper
import com.leboncoin.data.response.AlbumResp
import com.leboncoin.domain.models.Album
import javax.inject.Inject

/**
 * assure le mapping entre AlbumResp et Album
 */
class  AlbumMapper @Inject constructor() {

    fun toAlbumModel(albumRespList: List<AlbumResp>) : List<Album>{
        return  toAlbums(albumRespList)
    }

    private fun toAlbums(list: List<AlbumResp>) : List<Album>{
        return list.map { toAlbum(it) }
    }
    private fun toAlbum(albumResp: AlbumResp):Album{
        return Album(albumResp.albumId,albumResp.id,albumResp.title,albumResp.url,albumResp.thumbnailUrl)
    }
}