package com.leboncoin.leboncoin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.domain.models.Album
import com.leboncoin.leboncoin.R
import com.squareup.picasso.Picasso

class AlbumsListAdapter(private val mList: List<Album>) : RecyclerView.Adapter<AlbumsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = mList[position]
        Picasso.get()
            .load(album.thumbnailUrl)
            .into(holder.imageView);
        holder.textView.text = album.title
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.album_pic)
        val textView: TextView = itemView.findViewById(R.id.album_title)
    }
}
