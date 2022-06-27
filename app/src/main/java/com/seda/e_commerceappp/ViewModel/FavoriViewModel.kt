package com.seda.e_commerceappp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.repository.FavoriRepository
import kotlinx.coroutines.launch

class FavoriViewModel  (private val productRepository: FavoriRepository): ViewModel() {



    fun insert1(urun: productsItem) = viewModelScope.launch {
        productRepository.insertFavori(urun)

    }
    fun delete(meal: productsItem) = viewModelScope.launch {
        productRepository.delete(meal)
    }

    val getAllTomeal = productRepository.getAllMeal()


}
