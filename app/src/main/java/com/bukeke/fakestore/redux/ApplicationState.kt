package com.bukeke.fakestore.redux

import com.bukeke.fakestore.model.domain.Product

data class ApplicationState(
    val products: List<Product> = emptyList()
)
