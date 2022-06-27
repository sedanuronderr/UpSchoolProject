package com.seda.e_commerceappp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seda.e_commerceappp.model.productsItem

@Database(entities = [productsItem::class], version = 4)
@TypeConverters(MealTypeConverter::class)
abstract class FavoriDatabase : RoomDatabase(){
    abstract fun favoriDao():FavoriDao



    companion object{


        @Volatile
        var INSTANCE :FavoriDatabase? = null


        private val lock = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(lock) {
            INSTANCE ?: makeDatabase(context).also {
                INSTANCE=it
            }
        }
        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavoriDatabase::class.java,
            "favori.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}