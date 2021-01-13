package com.example.employeeandroomapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.employeeandroomapp.R
import com.example.employeeandroomapp.repository.util.Status

@BindingAdapter("roomStatus")
fun showRoomStatus(textView: TextView, status: Boolean)
{
    if(status)
        textView.text = "Occupied"
    else
        textView.text = "Free"
}

@BindingAdapter("roomCapacity")
fun showRoomStatus(textView: TextView, capacity: Int)
{
    textView.text = "Capacity:$capacity"
}

@BindingAdapter("imageUrl")
fun showPhoto(imageView: ImageView, url: String?) {

    val imageUri = url?.toUri()?.buildUpon()?.scheme("https")?.build()

    Glide.with(imageView.context)
        .load(imageUri)
        .placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_broken_image)
        .into(imageView)
}

@BindingAdapter("searchStatus")
fun bindStatus(statusImageView: ImageView, status: Status?) {

    // to show status to user in case of data being downloaded/ offline/ error
    when (status) {
        Status.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        Status.SUCCESS -> {
            statusImageView.visibility = View.GONE
        }

        Status.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("isVisible")
fun showOrHideView(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

