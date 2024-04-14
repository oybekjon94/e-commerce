package com.oybekdev.e_commerce.data.store


import com.oybekdev.e_commerce.data.api.auth.dto.UserDto

import javax.inject.Inject

class UserStore @Inject constructor() : BaseStore<UserDto>("user", UserDto::class.java)
