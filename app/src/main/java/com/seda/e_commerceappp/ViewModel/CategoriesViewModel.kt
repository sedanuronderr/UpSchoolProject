package com.seda.e_commerceappp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seda.e_commerceappp.model.*
import com.seda.e_commerceappp.retrofit.Instance1
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesViewModel :ViewModel(){

    private  var categoriData= MutableLiveData<List<CategoriItem>>()
    val categori: LiveData<List<CategoriItem>>
        get() = categoriData
    init{
        productsItems()
    }

    fun productsItems() = viewModelScope.launch{

        Instance1.api.getCategories().enqueue(object :Callback<categori> {
            override fun onResponse(call: Call<categori>, response: Response<categori>) {
                if(response.isSuccessful){
                    categoriData.value = response.body()
                    Log.e("sorun","${categoriData.value}")
                }
            }

            override fun onFailure(call: Call<categori>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })


    }



}
