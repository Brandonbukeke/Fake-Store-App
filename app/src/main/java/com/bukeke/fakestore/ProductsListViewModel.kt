package com.bukeke.fakestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bukeke.fakestore.model.domain.Product
import com.bukeke.fakestore.redux.ApplicationState
import com.bukeke.fakestore.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    private val productsRepository:ProductsRepository
    ):ViewModel() {



    fun refreshProducts() = viewModelScope.launch{
        val products:List<Product> = productsRepository.fetchAllProducts()
        store.update {applicationState ->
            return@update applicationState.copy(
                products = products
            )
        }

    }
}