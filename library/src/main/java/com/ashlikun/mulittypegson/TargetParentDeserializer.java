package com.ashlikun.mulittypegson;

import com.ashlikun.gson.GsonExtendsKt;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/4/24 0024　下午 2:11
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：反序列化外部type字段的adapter,会先解析这一个层级的数据
 * 由外到内
 */
public class TargetParentDeserializer implements JsonDeserializer<Object> {

    //新建一个Gson,不再对AdaptedParentClass进行注册
    private Gson gson;
    //不同类型的解析器，这里要给他指定类型
    private TargetDeserializer targetDeserializer;
    MultiTypeGsonBuilder multiTypeGsonBuilder;
    /**
     * 包含type类的类型
     */
    protected Type targetParentClass;

    protected TargetParentDeserializer(MultiTypeGsonBuilder multiTypeGsonBuilder, Type targetParentClass, TargetDeserializer targetDeserializer) {
        this.targetDeserializer = targetDeserializer;
        this.targetParentClass = targetParentClass;
        this.multiTypeGsonBuilder = multiTypeGsonBuilder;
        gson = GsonExtendsKt.createX(multiTypeGsonBuilder.buildTager());
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = (JsonObject) json;
        String typeValue = null;
        if (jsonObject.has(multiTypeGsonBuilder.typeElementName)) {
            //如果包含type字段，就把对应的value传递给下一层级
            typeValue = multiTypeGsonBuilder.getString(jsonObject.get(multiTypeGsonBuilder.typeElementName));
            targetDeserializer.setParentElementValue(typeValue);
        }
        Object item = gson.fromJson(json, targetParentClass);
        onTargetParentItemDeserialize(item, typeValue);
        return item;
    }


    /**
     * 当目标类外层的类解析之后供子类调用
     *
     * @param item
     * @param typeElementValue
     */
    protected void onTargetParentItemDeserialize(Object item, String typeElementValue) {

    }
}
