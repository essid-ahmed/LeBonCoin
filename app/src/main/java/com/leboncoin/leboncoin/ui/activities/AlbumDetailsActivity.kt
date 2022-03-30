package com.leboncoin.leboncoin.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leboncoin.domain.models.Album
import com.leboncoin.leboncoin.R
import com.leboncoin.leboncoin.utils.album_intent_key
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_details.*

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)
        setUpUI(intent.getParcelableExtra<Album>(album_intent_key))

    }
    private fun setUpUI(album:Album? )
    {
        album.let {
            Picasso.get()
                .load(album?.url)
                .into(album_pic)
            album_title.text=album?.title
        }
    }
}