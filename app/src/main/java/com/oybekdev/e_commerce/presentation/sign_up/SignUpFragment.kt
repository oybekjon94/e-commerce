package com.oybekdev.e_commerce.presentation.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.databinding.FragmentSignUpBinding
import com.oybekdev.e_commerce.util.BaseFragment
import com.oybekdev.e_commerce.util.clearLightStatusBar
import com.oybekdev.e_commerce.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private val viewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progress.isVisible = isLoading
            register.text = if (isLoading) null else getString(R.string.sign_up_register)
        }
        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {

                SignUpViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                SignUpViewModel.Event.Error -> toast(R.string.error)
                SignUpViewModel.Event.AlreadyRegistered -> toast(R.string.already_registered)
                SignUpViewModel.Event.NavigateToHome -> toast(R.string.app_name)
            }
        }
    }


    private fun initUi() = with(binding) {
        //for clearStatusBar
        clearLightStatusBar()

        register.setOnClickListener {
            viewModel.signUp(
                username.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        singIn.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.signInFragment())
        }
    }
}