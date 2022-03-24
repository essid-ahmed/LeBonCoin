package com.leboncoin.data.db

import androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leboncoin.data.db.dao.AlbumDao
import com.leboncoin.data.response.AlbumResp


@Database(entities = [AlbumResp::class], version = 1)
abstract class AlbumsDB : RoomDatabase() {
    abstract fun AlbumDao(): AlbumDao
    companion object {
        @Volatile private var instance: AlbumsDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AlbumsDB::class.java, "albums.db")
            .build()
    }
}