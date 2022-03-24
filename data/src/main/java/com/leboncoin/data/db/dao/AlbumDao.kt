package com.leboncoin.data.db.dao

import androidx.room.*
import com.leboncoin.data.response.AlbumResp

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAll(): List<AlbumResp>

    @Query("SELECT count(*) from albums")
    fun getAlbumsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg albumResp: AlbumResp)
}