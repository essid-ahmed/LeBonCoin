package com.leboncoin.leboncoin.ui.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.result.AlbumResult
import com.leboncoin.leboncoin.R
import com.leboncoin.leboncoin.ui.adapters.AlbumsListAdapter
import com.leboncoin.leboncoin.ui.adapters.OnAlbumClickListener
import com.leboncoin.leboncoin.utils.album_intent_key
import com.leboncoin.leboncoin.viewmodels.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_albums_list.*
import kotlinx.android.synthetic.main.empty_view.*
import javax.inject.Inject

@AndroidEntryPoint
class AlbumsListActivity : AppCompatActivity(),OnAlbumClickListener {
    private val LIST_STATE_KEY = "LIST_STATE_KEY"
    private var mListState: Parcelable? = null
    private val Any.TAG: String
        get() {
            val tag = javaClass.simpleName
            return if (tag.length <= 23) tag else tag.substring(0, 23)
        }
    private lateinit var layoutManager : LinearLayoutManager
    @Inject lateinit var viewModel:AlbumsViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums_list)
    }

    override fun onResume() {
        if (mListState != null) {
            layoutManager.onRestoreInstanceState(mListState)
        }
        super.onResume()
            viewModel.getAlbums().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleAlbumsReception(it) }
                .addTo(compositeDisposable)
    }

     private fun onLoadSuccess(albums: List<Album>) {
        progress_bar.visibility= View.GONE
        layoutManager= LinearLayoutManager(this)
        albums_list.layoutManager =layoutManager
        val adapter = AlbumsListAdapter(albums,this)
        albums_list.adapter=adapter
        Log.d(TAG,"onLoadSuccess $albums")
     }

     private fun onLoadError() {
        progress_bar.visibility= View.GONE
        empty_view.visibility=View.VISIBLE
         Log.e(TAG,"onLoadError")

     }

     private fun onStartLoading() {
        progress_bar.visibility= View.VISIBLE
        Log.d(TAG,"onStartLoading")
    }
    private fun handleAlbumsReception(result: AlbumResult) {

        when (result) {
            is AlbumResult.Success -> {
                onLoadSuccess(result.albumList)
            }
            is AlbumResult.Failure -> {
                onLoadError()
            }
            is AlbumResult.Loading -> {
                onStartLoading()
            }

        }
    }
    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        mListState = layoutManager.onSaveInstanceState()
        outState.putParcelable(LIST_STATE_KEY, mListState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
            mListState = savedInstanceState.getParcelable<Parcelable>(LIST_STATE_KEY)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG,"onConfigChanged")
    }

    override fun onAlbumClick(album: Album) {
        Log.d(TAG,"onAlbumClick  $album")
        val detailsIntent = Intent(this,AlbumDetailsActivity::class.java)
        detailsIntent.putExtra(album_intent_key,album)
        startActivity(detailsIntent)
    }

}