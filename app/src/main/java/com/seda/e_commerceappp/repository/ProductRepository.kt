package com.example.todolist.repository

import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.db.ProductDao


class ProductRepository(private  val dao: ProductDao){
 suspend fun insertMeal(product: productsItem)=dao.insert(product)

 suspend  fun delete(productdelete: productsItem)= dao.delete(productdelete)


    fun getAllMeal()= dao.getAllProduct()
 suspend   fun update(productupdate: productsItem)=dao.updateSongs(productupdate)

}