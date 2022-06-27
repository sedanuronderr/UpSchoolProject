package com.seda.e_commerceappp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class price(@PrimaryKey(autoGenerate = true)
                 val id:Int,
                 val pricelist:String) {
}