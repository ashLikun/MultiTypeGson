package com.ashlikun.gson;

import com.ashlikun.gson.data.BigDecimalTypeAdapter;
import com.ashlikun.gson.data.BooleanTypeAdapter;
import com.ashlikun.gson.data.DoubleTypeAdapter;
import com.ashlikun.gson.data.FloatTypeAdapter;
import com.ashlikun.gson.data.IntegerTypeAdapter;
import com.ashlikun.gson.data.JSONArrayTypeAdapter;
import com.ashlikun.gson.data.JSONObjectTypeAdapter;
import com.ashlikun.gson.data.LongTypeAdapter;
import com.ashlikun.gson.data.StringNullAdapter;
import com.ashlikun.gson.data.StringTypeAdapter;
import com.ashlikun.gson.element.CollectionTypeAdapterFactory;
import com.ashlikun.gson.element.MapTypeAdapterFactory;
import com.ashlikun.gson.element.ReflectiveTypeAdapterFactory;
import com.ashlikun.mulittypegson.MultiTypeGsonBuilder;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TypeAdapters;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;

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
 * 数据类型	容错的范围	数据示例
 * bean	集合、字符串、布尔值、数值	[]、""、false、0
 * 集合	bean、字符串、布尔值、数值	{}、""、false、0
 * 字符串	bean、集合、布尔值、数值	{}、[]、false、0
 * 布尔值	bean、集合、字符串、数值	{}、[]、""、0
 * 数值	bean、集合、字符串、布尔值	{}、[]、""、false
 * <p>
 * 以上情况框架已经做了容错处理，具体处理规则如下：
 * <p>
 * 如果后台返回的类型和客户端定义的类型不匹配，框架就不解析这个字段
 * <p>
 * 如果客户端定义的是整数，但后台返回浮点数，框架就对数值进行取整并赋值给字段
 * <p>
 * 如果客户端定义布尔值，但是后台返回整数，框架则将非 0 的数值则赋值为 true，否则为 false
 * 如果字符串是null ，框架会变成""
 */


public class GsonHelper {
    private static JsonCallback jsonCallback;
    private static final HashMap<Type, InstanceCreator<?>> INSTANCE_CREATORS = new HashMap<>(0);

    public static JsonCallback getJsonCallback() {
        return jsonCallback;
    }

    public static void setJsonCallback(JsonCallback jsonCallback) {
        GsonHelper.jsonCallback = jsonCallback;
    }

    /**
     * 注册构造函数创建器
     *
     * @param type    对象类型
     * @param creator 实例创建器
     */
    public static void registerInstanceCreator(Type type, InstanceCreator<?> creator) {
        INSTANCE_CREATORS.put(type, creator);
    }

    /**
     * MultiTypeGson对应的数据类型
     */
    public static HashMap<String, Type> multiTypeClassMap = new HashMap<>();

    public static Gson getGson() {
        return getBuilder().create();
    }

    public static GsonBuilder getBuilder() {
        ConstructorConstructor constructor = new ConstructorConstructor(INSTANCE_CREATORS);
        return new GsonBuilder()
                //基本类型异常
                .registerTypeAdapterFactory(TypeAdapters.newFactory(boolean.class, Boolean.class, new BooleanTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(int.class, Integer.class, new IntegerTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(long.class, Long.class, new LongTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(float.class, Float.class, new FloatTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(double.class, Double.class, new DoubleTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(BigDecimal.class, new BigDecimalTypeAdapter()))
                .registerTypeAdapterFactory(new CollectionTypeAdapterFactory(constructor))
                .registerTypeAdapterFactory(new ReflectiveTypeAdapterFactory(constructor, FieldNamingPolicy.IDENTITY, Excluder.DEFAULT))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(JSONObject.class, new JSONObjectTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(JSONArray.class, new JSONArrayTypeAdapter()))
                //解析异常不抛出，但是会为null
                .registerTypeAdapterFactory(TypeAdapters.newFactory(String.class, new StringTypeAdapter()))
                //Map类型解析的一些问题
                .registerTypeAdapterFactory(new MapTypeAdapterFactory(constructor, false))
                .serializeNulls();
    }

    /**
     * 处理String为null的情况,返回""
     */
    public static GsonBuilder getBuilderNotNull() {
        ConstructorConstructor constructor = new ConstructorConstructor(INSTANCE_CREATORS);
        return new GsonBuilder()
                //基本类型异常
                .registerTypeAdapterFactory(TypeAdapters.newFactory(boolean.class, Boolean.class, new BooleanTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(int.class, Integer.class, new IntegerTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(long.class, Long.class, new LongTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(float.class, Float.class, new FloatTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(double.class, Double.class, new DoubleTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(BigDecimal.class, new BigDecimalTypeAdapter()))
                .registerTypeAdapterFactory(new CollectionTypeAdapterFactory(constructor))
                .registerTypeAdapterFactory(new ReflectiveTypeAdapterFactory(constructor, FieldNamingPolicy.IDENTITY, Excluder.DEFAULT))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(JSONObject.class, new JSONObjectTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(JSONArray.class, new JSONArrayTypeAdapter()))
                .registerTypeAdapterFactory(TypeAdapters.newFactory(String.class, new StringNullAdapter()))
                //Map类型解析的一些问题
                .registerTypeAdapterFactory(new MapTypeAdapterFactory(constructor, false))
                // 添加到自定义的类型解析适配器，因为在 GsonBuilder.create 方法中会对 List 进行反转，所以这里需要放到最后的位置上，这样就会优先解析
                .serializeNulls();
    }

    /**
     * 获取解析多种格式的gson
     *
     * @return
     */
    public static MultiTypeGsonBuilder getMultiType() {
        return new MultiTypeGsonBuilder(getBuilder());
    }

    /**
     * 处理String为null的情况,返回""
     */
    public static Gson getGsonNotNull() {
        return getBuilderNotNull().create();
    }


    /**
     * 获取解析多种格式的gson
     * 处理String为null的情况,返回""
     */
    public static MultiTypeGsonBuilder getMultiTypeNotNull() {
        return new MultiTypeGsonBuilder(getBuilderNotNull());
    }

}
