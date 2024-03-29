package com.example.finalplayground.ui.common

import android.content.res.Resources
import androidx.fragment.app.Fragment
import com.example.finalplayground.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showErrorBar(msg: String?) =
    view?.let {
        Snackbar.make(it, msg ?: getString(R.string.network_error), Snackbar.LENGTH_LONG).show()
    }

fun Float.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}
