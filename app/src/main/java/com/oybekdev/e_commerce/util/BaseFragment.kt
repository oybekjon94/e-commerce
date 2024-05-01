package com.oybekdev.e_commerce.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * This abstract class serves as a base for other fragments in the application.
 * Encapsulation: The base class encapsulates common logic for fragment initialization and
 * ViewBinding setup, reducing boilerplate code in subclasses.
 * Flexibility: By parameterizing the inflation logic, subclasses can customize the inflation
 * process as needed, allowing for reuse across different fragment implementations.
 */

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<V : ViewBinding>(
    private val inflate: Inflate<V>,
) : Fragment() {

    private lateinit var _binding: V
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

}