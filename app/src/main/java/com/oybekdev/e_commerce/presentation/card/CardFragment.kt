package com.oybekdev.e_commerce.presentation.card

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.databinding.FragmentCartBinding
import com.oybekdev.e_commerce.domain.model.Cart
import com.oybekdev.e_commerce.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment:BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    private val viewModel by viewModels<CartViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.carts.observe(viewLifecycleOwner){
            products.adapter = CartAdapter(it,this@CardFragment::onClick,this@CardFragment::set )
            empty.isVisible = it.isEmpty()
            content.isVisible = it.isNotEmpty()
        }
        viewModel.events.observe(viewLifecycleOwner){
            val message = when(it){
                CartViewModel.Event.BillingError -> R.string.fragment_cart_billing_error
                CartViewModel.Event.OrderError -> R.string.fragment_cart_billing_order
                CartViewModel.Event.OrderCreated -> {
                    findNavController().popBackStack()
                    R.string.fragment_cart_order_created
                }
            }
            Snackbar.make(binding.root,message,Snackbar.LENGTH_SHORT).show()
        }
        viewModel.billingLoading.observe(viewLifecycleOwner){
            promoCode.isVisible = it
            apply.text = if (it) null else getString(R.string.fragment_cart_apply)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            progress.isVisible = it
            checkOut.text = if (it) null else getString(R.string.fragment_cart_checkout)
        }
        viewModel.billing.observe(viewLifecycleOwner){ billing ->
            itemTotal.text = getString(R.string.price_2, billing.items)
            deliveryFee.text = getString(R.string.price_plus, billing.delivery)
            tax.text = getString(R.string.price_plus, billing.tax)
            binding.discount.text = getString(R.string.price_minus, billing.discount ?: 0.0)
            listOf(binding.discount, discountText).forEach {
                it.isVisible = billing.discount != null
            }
            ordalTotalText.text = getString(R.string.price_2, billing.total)
        }
    }

    private fun initUi() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack()
        }
        delete.setOnClickListener {
            viewModel.clear()
        }
        apply.setOnClickListener {
            viewModel.getBilling(promoCode.text.toString())
        }
        checkOut.setOnClickListener {
            viewModel.createOrder(promoCode.text.toString())
        }
    }
    private fun onClick(cart:Cart){
        findNavController().navigate(CardFragmentDirections.toDetailFragment(cart.id))
    }
    private fun set(cart:Cart){
        viewModel.set(cart)
    }
}