package com.leboncoin.data.di
import com.leboncoin.data.repositoryimpl.AlbumRepositoryImp
import com.leboncoin.domain.repository.IAlbumsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class  RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAlbumRepository(albumRepositoryImp: AlbumRepositoryImp): IAlbumsRepository
}




