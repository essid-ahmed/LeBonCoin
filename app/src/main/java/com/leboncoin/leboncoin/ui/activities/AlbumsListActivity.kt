package com.leboncoin.leboncoin.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.result.AlbumResult
import com.leboncoin.leboncoin.R
import com.leboncoin.leboncoin.utils.isNetworkAvailable
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
class AlbumsListActivity : AppCompatActivity() {
    @Inject lateinit var viewModel:AlbumsViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums_list)
    }

    override fun onResume() {
        super.onResume()
        if(isNetworkAvailable(this)) {
            viewModel.getAlbums().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleAlbumsReception(it) }
                .addTo(compositeDisposable)
        }else
        {
            Toast.makeText(this,getString(R.string.no_data_available),Toast.LENGTH_LONG).show()
        }
    }

     private fun onLoadSuccess(albums: List<Album>) {
        progress_bar.visibility= View.GONE
        //set data into recyclerview
         Log.e("TAG","onLoadSuccess $albums")

     }

     private fun onLoadError() {
        progress_bar.visibility= View.GONE
        empty_view.visibility=View.VISIBLE
         Log.e("TAG","onLoadError")

     }

     private fun onStartLoading() {
        progress_bar.visibility= View.VISIBLE
        Log.e("TAG","onStartLoading")
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
}