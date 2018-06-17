package com.ashlikun.mulittypegson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/4/24 0024　上午 11:20
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：根据type不同解析不同的json
 */
public class MultiTypeGsonBuilder {
    protected HashMap<String, Type> typeClassMap = new HashMap<>();
    private TargetDeserializer typeAdapter;
    protected String typeElementName;
    private Type targetParentClass;
    private Class targetClass;
    /**
     * 内部解析json
     */
    protected Gson parseGson;
    /**
     * 是否强制使用目标类上一层的type对应的value，因为存在目标类和上一层type对应的value不同
     */
    protected boolean forceUseParentValue = true;

    protected MultiTypeGsonBuilder() {
    }

    /**
     * 注册能够表示type类型的字段名
     */
    public MultiTypeGsonBuilder registerTypeElementName(String typeElementName) {
        this.typeElementName = typeElementName;
        return this;
    }

    /**
     * 注册type对应的value值以及整个value值对应的Class
     */
    public MultiTypeGsonBuilder registerTypeElementClass(String typeElementValue, Type classValue) {
        this.typeClassMap.put(typeElementValue, classValue);
        return this;
    }

    /**
     * 注册需要需要转换的类，通常这个类是一个父类(公共实现的类)
     * 默认为 {@link BaseMultiData}
     */
    public MultiTypeGsonBuilder registerTargetClass(Class targetClass) {
        this.targetClass = targetClass;
        return this;
    }

    /**
     * 注册包裹在被转换的类的上一层的类
     * 需要继承{@link BaseMultiParentData}或者{@link ImpBaseMultiParentData}
     */
    public MultiTypeGsonBuilder registerTargetParentClass(Type targetParentClass) {
        this.targetParentClass = targetParentClass;
        return this;
    }

    /**
     * 是否强制把外层的type字段的值设置给内部
     * 有的时候上一级和目标级都有这个{@link #typeElementName}字段
     */
    public MultiTypeGsonBuilder noForceUseParentValue() {
        forceUseParentValue = false;
        return this;
    }

    /**
     * 构建前检查
     */
    private void buildCheck() {
        if (typeAdapter == null) {
            typeAdapter = new TargetDeserializer(this);
        }
        if (targetClass == null) {
            targetClass = BaseMultiData.class;
        }
    }

    /**
     * 构建解析,解析上一级和目标类
     *
     * @return
     */
    public GsonBuilder build() {
        buildCheck();
        GsonBuilder gsonBuilder = GsonHelper.getGsonBuilder();
        gsonBuilder.registerTypeAdapter(targetClass, typeAdapter);
        if (targetParentClass != null) {
            gsonBuilder.registerTypeAdapter(targetParentClass, new TargetParentDeserializer(this, targetParentClass, typeAdapter));
        }
        parseGson = gsonBuilder.create();
        return gsonBuilder;
    }

    /**
     * 构建一个只解析内部的Gson
     */
    public GsonBuilder buildTager() {
        buildCheck();
        return GsonHelper.getGsonBuilder()
                .registerTypeAdapter(targetClass, typeAdapter);
    }

    protected String getString(JsonElement jsonElement) {
        return jsonElement.isJsonNull() ? "" : jsonElement.getAsString();
    }

    // 这个值是否注册过
    protected boolean checkHasRegistered(String typeValue) {
        return typeClassMap.containsKey(typeValue);
    }

    protected Type getRegistered(String typeValue) {
        return typeClassMap.get(typeValue);
    }
}
