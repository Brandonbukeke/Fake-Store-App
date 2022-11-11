package com.bukeke.fakestore.model.ui

import com.bukeke.fakestore.model.domain.Product

data class UiProduct(
    val product: Product,
    val isFavorite: Boolean = false
)
