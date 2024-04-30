package com.oybekdev.e_commerce.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.common.Constants
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.FragmentDetailBinding
import com.oybekdev.e_commerce.util.BaseFragment
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment:BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getProduct(args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding){
        viewModel.loading.observe(viewLifecycleOwner){
            loading.root.isVisible = it
            setButtonVisibility()
        }
        viewModel.error.observe(viewLifecycleOwner){
            error.root.isVisible = it
            setButtonVisibility()
        }
        viewModel.detail.observe(viewLifecycleOwner){
            val color = if (it.wishlist) R.color.orange else R.color.dark
            val resolved = ResourcesCompat.getColor(resources,color,null)
            favorite.setColorFilter(resolved)

            images.adapter = ImageAdapter(it.images)
            indicator.setupWithViewPager(images)
            indicator.apply {
                setPageSize(it.images.size)
                notifyDataChanged()
            }

            category.text = it.category
            title.text = it.title
            price.text = getString(R.string.price, it.price -(it.discount ?: 0.0))
            old.isVisible = it.discount != null
            old.text = getString(R.string.price_striked, it.discount ?: 0.0)

            reviews.text = getString(R.string.fragment_product_reviews, it.rating, it.reviews)
            deliveryContainer.isVisible = it.freeDelivery

            description.text = it.description

            products.adapter = RelatedAdapter(it.related,this@DetailFragment::onClick)

        }
        viewModel.count.observe(viewLifecycleOwner){
            count.text = it.toString()
        }
        viewModel.wishlist.observe(viewLifecycleOwner){
            setWishlist(it)
        }
    }

    private fun FragmentDetailBinding.setButtonVisibility() {
        add.isVisible = viewModel.loading.value == false && viewModel.error.value == false
        buttonDivider.isVisible = viewModel.loading.value == false && viewModel.error.value == false
    }

    private fun initUi() = with(binding){
        back.setOnClickListener {
            findNavController().popBackStack()
        }
        error.retry.setOnClickListener {
            viewModel.getProduct(args.id)
        }
        indicator.apply {
            val normalColor = ContextCompat.getColor(requireContext(), R.color.indicator_unchecked)
            val checkedColor = ContextCompat.getColor(requireContext(), R.color.indicator_checked)
            setSliderColor(normalColor, checkedColor)
            setSliderWidth(resources.getDimension(R.dimen.dp_12))
            setSliderHeight(resources.getDimension(R.dimen.dp_6))
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            notifyDataChanged()
        }
        plus.setOnClickListener {
            viewModel.increment()
        }
        minus.setOnClickListener {
            viewModel.decrement()
        }
        share.setOnClickListener {
            ShareCompat.IntentBuilder(requireContext())
                .setType("text/plain")
                .setChooserTitle(R.string.fragment_detail_share_title)
                .setText(Constants.HOST + "products/${args.id}")
                .startChooser()
        }

        favorite.setOnClickListener {
            viewModel.toggleWishlist()
        }
            //for the Cart Screen
        add.setOnClickListener {
            viewModel.set()
            Snackbar.make(root,R.string.fragment_detail_cart_added,Snackbar.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }
    private fun onClick(product:Product){
        findNavController().navigate(DetailFragmentDirections.toDetailFragment(product.id))
    }

    private fun setWishlist(wishlist:Boolean) = with(binding){
        val color = if (wishlist)R.color.orange else R.color.dark
        val resolved = ContextCompat.getColor(requireContext(),color)
        favorite.setColorFilter(resolved)
    }
}