package com.oybekdev.e_commerce.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.forEachIndexed
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.oybekdev.e_commerce.MainDirections
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.databinding.ActivityMainBinding
import com.oybekdev.e_commerce.domain.model.Destination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding


    private val navController get() = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        subscribeToLiveData()
    }

    private fun initUi() = with(binding) {
        navigation.setupWithNavController(navController)

        navigation.setOnItemSelectedListener {

            var index:Int = 0
            navigation.menu.forEachIndexed { itemIndex, item ->
                if (it.itemId != item.itemId) return@forEachIndexed
                index = itemIndex
            }

            ConstraintSet().apply {
                clone(indicatorContainer)
                setHorizontalBias(indicator.id, index * 0.25F)

                val transition :Transition= ChangeBounds()
                transition.interpolator = DecelerateInterpolator(1.0f)
                transition.duration = 500

                TransitionManager.beginDelayedTransition(indicatorContainer,transition)

                applyTo(indicatorContainer)
            }

            NavigationUI.onNavDestinationSelected(it,navController)
            return@setOnItemSelectedListener true
        }

        //for navigation visible
        navController.addOnDestinationChangedListener{_,destination, _ ->
            navigation.isVisible = listOf(
                R.id.onboardingFragment,
                R.id.signInFragment,
                R.id.signUpFragment,
                R.id.detailFragment,
            ).all{it != destination.id}
        }
    }

    private fun subscribeToLiveData() {
        viewModel.events.observe(this) {
            when (it) {
                is MainViewModel.Event.NavigateTo -> navigateTo(it.destination)
            }
        }
    }

    private fun navigateTo(destination: Destination) {
        if (navController.currentDestination?.id == R.id.detailFragment) return
        when (destination) {
            Destination.Auth -> navController.navigate(MainDirections.tooSignInFragment())
            Destination.Home -> navController.navigate(MainDirections.toHomeFragment())
            Destination.Onboarding -> navController.navigate(MainDirections.toOnboardingFragment())
        }
    }
}