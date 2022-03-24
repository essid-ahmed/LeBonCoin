package com.leboncoin.domain.usecase

import com.leboncoin.domain.models.Album
import com.leboncoin.domain.repository.IAlbumsRepository
import com.leboncoin.domain.result.AlbumResult
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *
 */
class GetAlbumsUseCase @Inject constructor(private val repo: IAlbumsRepository){

    fun execute(): Observable<AlbumResult> {
        return   repo.getAlbums()
            .toObservable()
            .map { onSuccessGettingAlbums(it) }
            .onErrorReturn { onFailureGettingAlbums(it) }
            .startWith(AlbumResult.Loading)
    }

    private fun onSuccessGettingAlbums(albums: List<Album>) : AlbumResult{
        repo.putAlbums(albums)?.let{it.subscribeOn(Schedulers.io())
            .subscribe({},{})}
       return AlbumResult.Success(albums)
    }

    private fun onFailureGettingAlbums(throwable: Throwable):AlbumResult{
        if(!repo.isDbEmpty())
        {
            return AlbumResult.Success(repo.getLocalAlbums())
        }
        return AlbumResult.Failure(throwable)
    }
}