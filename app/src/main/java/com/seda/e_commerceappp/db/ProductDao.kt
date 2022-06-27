package com.seda.e_commerceappp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.seda.e_commerceappp.model.productsItem

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend   fun insert(producttodo : productsItem)

    @Delete
 suspend  fun delete(todo: productsItem)
    //Flow  eşzamansız olarak hesaplanabilen bir veri akışıdır .
    // Yayılan değerler aynı türden olmalıdır. Örneğin a Flow<Int>, tamsayı değerleri yayan bir akıştır.

    @Query("SELECT * FROM productInformation")
 fun getAllProduct(): LiveData<List<productsItem>>

    @Update
   suspend fun updateSongs(update: productsItem)
}