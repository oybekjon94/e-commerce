package com.oybekdev.e_commerce.data.store

import javax.inject.Inject

class RecentsStore @Inject constructor() : BaseStore<Array<String>>("searches",Array<String>::class.java){
}