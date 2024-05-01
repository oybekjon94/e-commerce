package com.oybekdev.e_commerce.data.store

import com.oybekdev.e_commerce.domain.model.Cart
import javax.inject.Inject

class CartStore @Inject constructor():BaseStore<Array<Cart>>("cart", Array<Cart>::class.java)