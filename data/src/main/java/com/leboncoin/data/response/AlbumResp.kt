package com.leboncoin.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * représentre la réponse du WS (un album)
 */
@Entity(tableName = "albums")
data class AlbumResp(val albumId :Int, @PrimaryKey val id:Int, val title:String, val url:String, val thumbnailUrl:String)
