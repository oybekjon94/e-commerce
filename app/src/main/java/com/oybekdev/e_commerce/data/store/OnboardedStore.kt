package com.oybekdev.e_commerce.data.store

import javax.inject.Inject

class OnboardedStore @Inject constructor() : BaseStore<Boolean>("onboarded", Boolean::class.java)