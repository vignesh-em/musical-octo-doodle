package com.example.coin.data.source.database

import androidx.room.TypeConverter
import com.example.coin.data.models.Type

class CryptoTypeConverter {

    @TypeConverter
    fun toType(type: String) = enumValueOf<Type>(type)

    @TypeConverter
    fun fromType(type: Type) = type.name
}