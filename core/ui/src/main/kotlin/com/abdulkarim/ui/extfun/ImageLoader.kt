package com.abdulkarim.ui.extfun

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun Picasso.loadImage(drawable:Int, url:String, imageView: ImageView) {
    if (url.isEmpty()) return
    this.load(url).placeholder(drawable).into(imageView)
}

fun ImageView.loadImage(placeholder:Int, url : String) {
    if (url.isEmpty()) return
    Picasso.get().load(url).placeholder(placeholder).into(this)
}