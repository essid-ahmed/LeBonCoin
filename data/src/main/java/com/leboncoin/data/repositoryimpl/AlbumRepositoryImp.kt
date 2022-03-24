package com.leboncoin.data.repositoryimpl
import com.leboncoin.data.api.LeBonCoinApi
import com.leboncoin.data.mapper.AlbumMapper
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.repository.IAlbumsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * implémentation du repository, permet de récuper les albums via l'api et la base, et d'enregistrer nos albums dans la base
 */
class AlbumRepositoryImp @Inject constructor(private val api:LeBonCoinApi,private val albumMapper: AlbumMapper) : IAlbumsRepository {

    /**
     * récupére les albums et assure le mapping de l'objet #AlbumResp vers #Album
     */
    override fun getAlbums(): Single<List<Album>> {
        return api.getAlbums().map {
            albumMapper.toAlbumModel(it)
        }
    }
    override fun isDbEmpty(): Boolean {
        return true;
    }


}