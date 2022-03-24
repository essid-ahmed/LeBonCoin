package com.leboncoin.leboncoin.viewmodels

import com.leboncoin.domain.result.AlbumResult
import com.leboncoin.domain.usecase.GetAlbumsUseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * classe qui permet d'appeler notre use case et d'informer l'activité
 *
 */
open class AlbumsViewModel @Inject constructor(private val getAlbumsUseCase: GetAlbumsUseCase){
    fun getAlbums() :Observable<AlbumResult>  {
        return getAlbumsUseCase.execute()
    }
}