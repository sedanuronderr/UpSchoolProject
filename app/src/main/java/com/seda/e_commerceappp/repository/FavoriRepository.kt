package com.seda.e_commerceappp.repository

import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.db.FavoriDao

class FavoriRepository (private  val dao: FavoriDao){
    suspend fun insertFavori(favori: productsItem) = dao.insert(favori)

    suspend fun delete(favoridelete: productsItem)= dao.delete(favoridelete)


    fun getAllMeal()= dao.getAllfavori()

}