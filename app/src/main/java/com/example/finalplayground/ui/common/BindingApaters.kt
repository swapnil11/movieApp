package com.example.finalplayground.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.finalplayground.R

/**
 * Defines binding adapter downloadURL which can be used in the xml directly along with
 * data binding to specify download url which is passed along to this function when the XML is
 * inflated and the corresponding image get downloaded using glide and added to the imageview.
 */
@BindingAdapter("downloadUrl")
fun loadImage(imageView: ImageView, url: String?) =
    Glide.with(imageView).load(url).placeholder(R.drawable.placeholder)
        .into(imageView)
