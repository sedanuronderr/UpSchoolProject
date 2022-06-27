package com.seda.e_commerceappp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seda.e_commerceappp.model.products
import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.retrofit.Instance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel():ViewModel() {

    private  var productsLiveData= MutableLiveData<List<productsItem>>()
    val product: LiveData<List<productsItem>>
        get() = productsLiveData
    init{
productsItems()
    }

fun productsItems() = viewModelScope.launch{



   Instance.api.getProducts().enqueue(object : Callback<products> {
        override fun onResponse(call: Call<products>, response: Response<products>) {
          if(response.isSuccessful){
              productsLiveData.value = response.body()
              Log.e("sorun","${productsLiveData.value}")
          }
        }

        override fun onFailure(call: Call<products>, t: Throwable) {
Log.e("sorun1","$t")
        }


    })
}

}