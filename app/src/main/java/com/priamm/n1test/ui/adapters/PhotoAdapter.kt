package com.priamm.n1test.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.priamm.n1test.R
import com.priamm.n1test.extensions.inflate
import com.squareup.picasso.Picasso

class PhotoAdapter(private val photos: List<String?>) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val itemView = collection.inflate(R.layout.item_photo) as AppCompatImageView
        collection.addView(itemView)
        Picasso
            .get()
            .load(photos[position])
            .placeholder(R.mipmap.placeholder_image)
            .error(R.mipmap.placeholder_image)
            .into(itemView)
        return itemView
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return photos.size
    }
}