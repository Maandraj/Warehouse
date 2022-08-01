package com.maandraj.catalog.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

internal fun Fragment.snackbar(view: View, message: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, message, length).show()
}

