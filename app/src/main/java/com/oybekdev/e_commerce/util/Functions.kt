package com.oybekdev.e_commerce.util

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: Int) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
