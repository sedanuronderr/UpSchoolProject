package com.seda.e_commerceappp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "productInformation")
data class productsItem(
    @PrimaryKey
    val id: Int,
    val category: String?,
    val description: String?,

    val image: String?,
    val price: String?,
    val rating: Rating?,
    val title: String?
):Serializable