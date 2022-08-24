package com.ashlikun.gson

import com.ashlikun.gson.element.CollectionTypeAdapterFactory
import com.ashlikun.gson.element.MapTypeAdapterFactory
import com.ashlikun.gson.element.ReflectiveTypeAdapterFactory
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.internal.ConstructorConstructor
import com.google.gson.internal.Excluder
import java.lang.reflect.Field
import java.lang.reflect.Type

/**
 * 作者　　: 李坤
 * 创建时间: 2022/5/5　20:39
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：
 */
inline fun Any?.toJson() = GsonHelper.getGsonNotNull().toJson(this)
inline fun Any?.toJsonOrNull() = GsonHelper.getGson().toJson(this)

@Throws(JsonSyntaxException::class)
inline fun <T> String.fromJson(typeOfT: Type) = GsonHelper.getGsonNotNull().fromJson<T>(this, typeOfT)

@Throws(JsonSyntaxException::class)
inline fun <T> String.fromJsonOrNull(typeOfT: Type) = GsonHelper.getGson().fromJson<T>(this, typeOfT)

/**
 * 创建Gson
 * 使用自定义 CollectionTypeAdapterFactory 和 ReflectiveTypeAdapterFactory  和 MapTypeAdapterFactory
 */
inline fun GsonBuilder.createX(): Gson {
    val collectionTypeAdapterFactory = CollectionTypeAdapterFactory(null)
    val reflectiveTypeAdapterFactory = ReflectiveTypeAdapterFactory(null, null, null)
    val mapTypeAdapterFactory = MapTypeAdapterFactory(null, false)
    return apply {
        registerTypeAdapterFactory(collectionTypeAdapterFactory)
        registerTypeAdapterFactory(reflectiveTypeAdapterFactory)
        //Map类型解析的一些问题
        registerTypeAdapterFactory(mapTypeAdapterFactory)
    }.create().apply {
        val constructor =
            (GsonExtends.getFieldValue(this, "constructorConstructor") as? ConstructorConstructor)
                ?: ConstructorConstructor(GsonHelper.INSTANCE_CREATORS)
        //从新赋值一些属性
        collectionTypeAdapterFactory.mConstructorConstructor = constructor
        reflectiveTypeAdapterFactory.mConstructorConstructor = constructor
        reflectiveTypeAdapterFactory.mFieldNamingPolicy = fieldNamingStrategy()
        reflectiveTypeAdapterFactory.mExcluder = excluder()
        mapTypeAdapterFactory.constructorConstructor = constructor
    }
}

object GsonExtends {
    fun getField(claxx: Class<*>?, fieldName: String): Field? {
        var claxx: Class<*>? = claxx
        if (fieldName.isEmpty()) {
            return null
        }
        var ee: Exception? = null
        while (claxx != null && claxx != Any::class.java) {
            try {
                val f = claxx.getDeclaredField(fieldName)
                if (f != null) {
                    return f
                }
            } catch (e: NoSuchFieldException) {
                ee = e
            }
            claxx = claxx.superclass
        }
        ee?.printStackTrace()
        return null
    }

    fun getFieldValue(obj: Any?, fieldName: String): Any? {
        if (obj == null) return null
        if (fieldName.isEmpty()) {
            return null
        }
        var ee: Exception? = null
        try {
            val field = getField(obj.javaClass, fieldName)
            if (field != null) {
                field.isAccessible = true
                return field[obj]
            }
        } catch (e: IllegalAccessException) {
            ee = e
        }
        ee?.printStackTrace()
        return null
    }
}