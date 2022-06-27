package com.seda.e_commerceappp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seda.e_commerceappp.model.productsItem

@Database(entities = [productsItem::class],  version = 4, exportSchema = true)
@TypeConverters(MealTypeConverter::class)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao():ProductDao



    companion object{


        @Volatile
        var INSTANCE :ProductDatabase? = null


        private val lock = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(lock) {
            INSTANCE ?: makeDatabase(context).also {
                INSTANCE=it
            }
        }
        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            "meal.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}