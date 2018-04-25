package com.ashlikun.mulittypegson;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/4/24 0024　下午 1:46
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：目标类的反序列化
 */
public class TargetDeserializer implements JsonDeserializer {
    private final MultiTypeGsonBuilder multiTypeGsonBuilder;
    //上一级的type对应的value
    private String upperLeveElementValue;

    protected TargetDeserializer(MultiTypeGsonBuilder multiTypeGsonBuilder) {
        this.multiTypeGsonBuilder = multiTypeGsonBuilder;
    }

    public void setUpperLeveElementValue(String upperLeveElementValue) {
        this.upperLeveElementValue = upperLeveElementValue;
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = multiTypeGsonBuilder.parseGson;
        //
        JsonElement jsonElement = null;
        if (json.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) json;
            jsonElement = jsonObject.get(multiTypeGsonBuilder.typeElementName);
        }
        Object item;
        String contentType = null;

        //如果强制使用上一层级的value  或者当前类型没有找到type:""形式
        if (multiTypeGsonBuilder.forceUseUpperTypeValue || jsonElement == null) {
            contentType = upperLeveElementValue;
        } else if (jsonElement != null) {
            //jsonObject已经包含了"type":""的形式
            contentType = multiTypeGsonBuilder.getString(jsonElement);
        }

        // 未注册的类型直接返回null
        if (!multiTypeGsonBuilder.checkHasRegistered(contentType)) {
            return null;
        }

        item = gson.fromJson(json, multiTypeGsonBuilder.getRegistered(contentType));
        onTargetItemDeserialize(item, contentType);
        return item;
    }


    /**
     * 当目标类解析之后供子类调用
     *
     * @param item
     * @param typeElementValue
     */
    protected void onTargetItemDeserialize(Object item, String typeElementValue) {

    }
}
