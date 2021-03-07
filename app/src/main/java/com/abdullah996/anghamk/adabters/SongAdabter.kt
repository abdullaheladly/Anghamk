package com.abdullah996.anghamk.adabters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdullah996.anghamk.R
import com.abdullah996.anghamk.data.entities.Song
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class SongAdabter @Inject constructor(
        private val glide:RequestManager
) :BaseSongAdapter(R.layout.list_item) {

    override val differ=AsyncListDiffer(this,diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.itemView.apply {
            tvPrimary.text = song.title
            tvSecondary.text = song.subtitle
            glide.load(song.imageUrl).into(ivItemImage)

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }


}
