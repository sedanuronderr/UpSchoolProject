package com.seda.e_commerceappp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.seda.e_commerceappp.model.Rating

@TypeConverters
class MealTypeConverter {
    @TypeConverter
    fun fromAnyToString(attribute:Any?):String{
        if(attribute == null){
           return ""
        }
        return attribute as String
    }
    @TypeConverter
    fun fromStringtoAny(attribute:String?):Any{
        if(attribute == null){
            return ""
        }
        return attribute
    }
    @TypeConverter
    fun fromRating(rating: Rating):Double{
        return rating.rate
    }

    @TypeConverter
    fun toRating(rating: Double):Rating{
        return Rating(rating)
    }

}