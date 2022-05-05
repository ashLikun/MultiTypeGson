package com.ashlikun.gson

import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

/**
 * 作者　　: 李坤
 * 创建时间: 2022/5/5　20:39
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：
 */
inline fun Any.toJson() = GsonHelper.getGsonNotNull().toJson(this)
inline fun Any.toJsonOrNull() = GsonHelper.getGson().toJson(this)

@Throws(JsonSyntaxException::class)
fun <T> String.fromJson(typeOfT: Type) = GsonHelper.getGsonNotNull().fromJson<T>(this, typeOfT)
fun <T> String.fromJsonOrNull(typeOfT: Type) = GsonHelper.getGson().fromJson<T>(this, typeOfT)