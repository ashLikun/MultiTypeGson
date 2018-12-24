package com.ashlikun.gson;

import com.ashlikun.mulittypegson.MultiTypeGsonBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author　　: 李坤
 * 创建时间: 2018/12/24 17:15
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：gson的默认值处理
 * <p>
 * <p>
 * gson的注解
 * @Expose 是否序列化与反序列化
 * @SerializedName 序列化时候的名称
 */


public class GsonHelper {

    public static Gson getGson() {
        return getBuilder().create();
    }

    public static GsonBuilder getBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(int.class, new JsonTypeAdapter.IntegerTypeAdapter())
                .registerTypeAdapter(long.class, new JsonTypeAdapter.LongTypeAdapter())
                .registerTypeAdapter(float.class, new JsonTypeAdapter.FloatTypeAdapter())
                .registerTypeAdapter(double.class, new JsonTypeAdapter.DoubleTypeAdapter())
                .registerTypeAdapter(short.class, new JsonTypeAdapter.ShortTypeAdapter())
                .registerTypeAdapterFactory(StringNullAdapter.newFactory())
                .serializeNulls();
    }

    /**
     * 不处理String为null的情况
     *
     * @return
     */
    public static Gson getGsonStrNull() {
        return getBuilderStrNull().create();
    }

    public static GsonBuilder getBuilderStrNull() {
        return new GsonBuilder()
                .registerTypeAdapter(int.class, new JsonTypeAdapter.IntegerTypeAdapter())
                .registerTypeAdapter(long.class, new JsonTypeAdapter.LongTypeAdapter())
                .registerTypeAdapter(String.class, new JsonTypeAdapter.StringTypeAdapter())
                .registerTypeAdapter(float.class, new JsonTypeAdapter.FloatTypeAdapter())
                .registerTypeAdapter(double.class, new JsonTypeAdapter.DoubleTypeAdapter())
                .registerTypeAdapter(short.class, new JsonTypeAdapter.ShortTypeAdapter())
                .serializeNulls();
    }

    /**
     * 获取解析多种格式的gson
     *
     * @return
     */
    public static MultiTypeGsonBuilder getMultiTypeGsonBuilder() {
        return new MultiTypeGsonBuilder();
    }
}
