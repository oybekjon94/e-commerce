package com.oybekdev.e_commerce.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.databinding.FragmentOnboardingBinding
import com.oybekdev.e_commerce.util.BaseFragment
import com.oybekdev.e_commerce.util.clearLightStatusBar
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment :
    BaseFragment<FragmentOnboardingBinding>(FragmentOnboardingBinding::inflate) {
    private val adapter = OnBoardingAdapter() //har safar yaralmasligi uchun
    private val viewModel by viewModels<OnBoardingViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() = with(binding) {
        //clearStatusBar
        clearLightStatusBar()

        pager.adapter = adapter

        indicatorView.apply {
            val normalColor = ContextCompat.getColor(requireContext(), R.color.indicator_unchecked)
            val checkedColor = ContextCompat.getColor(requireContext(), R.color.indicator_checked)
            setSliderColor(normalColor, checkedColor)
            setSliderWidth(resources.getDimension(R.dimen.dp_10))
            setSliderHeight(resources.getDimension(R.dimen.dp_8))
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setPageSize(adapter.itemCount)
            notifyDataChanged()
        }

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                //callback
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                indicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) { //callback
                super.onPageSelected(position)
                indicatorView.onPageSelected(position)
                next.text = if (position == adapter.itemCount - 1) { //-1 oxirgi page
                    getString(R.string.fragment_onboarding_get_started)
                } else {
                    getString(R.string.fragment_onboarding_next)
                }
            }
        })

        next.setOnClickListener {
            if (pager.currentItem == adapter.itemCount - 1) { //oxirgi page bolsa
                viewModel.onboarded()
                findNavController().navigate(OnboardingFragmentDirections.toSignInFragment())
            } else {
                pager.setCurrentItem(pager.currentItem + 1, true)
            }
        }
    }
}
