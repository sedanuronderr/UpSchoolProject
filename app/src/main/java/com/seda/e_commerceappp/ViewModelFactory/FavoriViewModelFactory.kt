package com.seda.e_commerceappp.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seda.e_commerceappp.ViewModel.FavoriViewModel
import com.seda.e_commerceappp.repository.FavoriRepository

class FavoriViewModelFactory (val mealRepository: FavoriRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriViewModel(mealRepository) as T
    }

}
