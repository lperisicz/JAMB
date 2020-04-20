package com.perisic.luka.jamb.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("android:drawableStart")
fun setTextViewResource(textView: TextView, resource: Int?) {
    resource?.let {
        textView.setCompoundDrawablesWithIntrinsicBounds(it, 0, 0, 0)
    }
}

@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int?) {
    resource?.let {
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, resource))
    }
}