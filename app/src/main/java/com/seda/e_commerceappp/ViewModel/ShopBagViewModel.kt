package com.seda.e_commerceappp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.repository.ProductRepository
import com.seda.e_commerceappp.model.productsItem
import kotlinx.coroutines.launch

class ShopBagViewModel (private val productRepository: ProductRepository): ViewModel() {
    private var mealdetailmutableList = MutableLiveData<productsItem>()
    val responsemealdetail: LiveData<productsItem>
        get() = mealdetailmutableList


    fun insert(urun: productsItem) = viewModelScope.launch {
        productRepository.insertMeal(urun)

    }
    fun delete(meal: productsItem) = viewModelScope.launch {
        productRepository.delete(meal)
    }

    val getAllTomeal = productRepository.getAllMeal()

    fun update(update: productsItem) = viewModelScope.launch {
        productRepository.update(update)
    }



}