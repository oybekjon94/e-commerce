package com.oybekdev.e_commerce.data.store


import javax.inject.Inject

class TokenStore @Inject constructor() : BaseStore<String>("token", String::class.java)
