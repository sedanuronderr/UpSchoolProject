package com.seda.e_commerceappp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.seda.e_commerceappp.model.productsItem



@Dao
interface FavoriDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun insert(favoritodo : productsItem)

    @Delete
   suspend  fun delete(favoridelete: productsItem)
    //Flow  eşzamansız olarak hesaplanabilen bir veri akışıdır .
    // Yayılan değerler aynı türden olmalıdır. Örneğin a Flow<Int>, tamsayı değerleri yayan bir akıştır.

    @Query("SELECT * FROM productInformation")
    fun getAllfavori():LiveData<List<productsItem>>
}