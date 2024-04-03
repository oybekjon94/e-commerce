package com.oybekdev.e_commerce.presentation.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progress.isInvisible = isLoading
        }
        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {

                SignInViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                SignInViewModel.Event.Error -> toast(R.string.error)
                SignInViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
            }
        }

    }

    private fun initUi() = with(binding) {
        signIn.setOnClickListener {
            viewModel.signIn(username.text.toString(), password.text.toString())
        }
    }

    private fun toast(message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}

