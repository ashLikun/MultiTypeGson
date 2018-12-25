package com.ashlikun.gson;

import android.text.TextUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author　　: 李坤
 * 创建时间: 2018/12/24 17:02
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：json转为对象时调用,实现JsonDeserializer<>接口
 */

public class JsonTypeAdapter {

    public static class IntegerTypeAdapter implements JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {
                return null;
            }
            try {
                if (json.isJsonNull()) {
                    return null;
                } else {
                    return json.getAsInt();
                }
            } catch (Exception e) {
                return null;
            }
        }

    }

    public static class StringTypeAdapter implements JsonDeserializer<String> {
        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {
                return null;
            }
            try {
                String value = json.getAsJsonPrimitive().getAsString();
                if (value == null || TextUtils.equals(value.toUpperCase(), "NULL")) {
                    return null;
                } else if (value.length() == 0) {
                    return StringNullAdapter.NULL;
                } else {
                    return value;
                }
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static class LongTypeAdapter implements JsonDeserializer<Long> {
        @Override
        public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {
                return null;
            }
            try {
                if (json.isJsonNull()) {
                    return null;
                } else {
                    return json.getAsLong();
                }
            } catch (Exception e) {
                return null;
            }
        }


    }

    public static class DoubleTypeAdapter implements JsonDeserializer<Double> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {
                return null;
            }
            try {
                if (json.isJsonNull()) {
                    return null;
                } else {
                    return json.getAsDouble();
                }
            } catch (Exception e) {
                return null;
            }
        }


    }

    public static class FloatTypeAdapter implements JsonDeserializer<Float> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {
                return null;
            }
            try {
                if (json.isJsonNull()) {
                    return null;
                } else {
                    return json.getAsFloat();
                }
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static class ShortTypeAdapter implements JsonDeserializer<Short> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Short deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {
                return null;
            }
            String value = json.getAsJsonPrimitive().getAsString();
            try {
                if (json.isJsonNull()) {
                    return null;
                } else {
                    return json.getAsShort();
                }
            } catch (Exception e) {
                return null;
            }
        }
    }

}
