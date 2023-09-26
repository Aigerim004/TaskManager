package com.example.taskmanager.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskmanager.R

fun ImageView.loadImage(url: String){
    Glide.with(this).load(url).placeholder(R.drawable.ic_user).into(this)
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}