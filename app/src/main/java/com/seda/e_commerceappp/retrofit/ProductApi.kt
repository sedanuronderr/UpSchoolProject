package com.seda.e_commerceappp.retrofit



import com.seda.e_commerceappp.model.categori
import com.seda.e_commerceappp.model.products
import com.seda.e_commerceappp.Utils.Constants

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET(Constants.END_POINT)
    fun getProducts(): Call<products>

    @GET(Constants.END_POINT)
 fun getProductsDetails(@Query("sort")  order:String): Call<List<products>>
    @GET(Constants.END_POINT1)
    fun getCategories(): Call<categori>
}