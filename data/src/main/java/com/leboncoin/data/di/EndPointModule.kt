package com.leboncoin.data.di

import com.leboncoin.data.api.EndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object EndPointModule {

    @Singleton
    @Provides
    fun providesEndPointModule(retrofit: Retrofit): EndPoint {
        return retrofit.create(EndPoint::class.java)
    }
}