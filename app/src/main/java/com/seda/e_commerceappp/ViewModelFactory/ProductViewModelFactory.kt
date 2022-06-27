package com.seda.e_commerceappp.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.repository.ProductRepository
import com.seda.e_commerceappp.ViewModel.ShopBagViewModel


class  ProductViewModelFactory(val mealRepository: ProductRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShopBagViewModel(mealRepository) as T
    }

}