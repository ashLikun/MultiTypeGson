package com.ashlikun.mulittypegson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/8/4.
 * gson的默认值处理
 */

public class JsonTypeAdapter {

    public static class IntegerTypeAdapter implements JsonDeserializer<Integer> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.getAsJsonPrimitive().isJsonNull()) {
                return null;
            }
            try {
                return json.getAsJsonPrimitive().getAsInt();
            } catch (Exception e) {
                return null;
            }
        }

    }

    public static class StringTypeAdapter implements JsonDeserializer<String> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {
                return null;
            }
            try {
                return json.getAsJsonPrimitive().getAsString();
            } catch (Exception e) {
                return null;
            }
        }

    }

    public static class LongTypeAdapter implements JsonDeserializer<Long> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.getAsJsonPrimitive().isJsonNull()) {
                return null;
            }
            try {
                return json.getAsJsonPrimitive().getAsLong();
            } catch (Exception e) {
                return null;
            }
        }


    }

    public static class DoubleTypeAdapter implements JsonDeserializer<Double> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.getAsJsonPrimitive().isJsonNull()) {
                return null;
            }
            try {
                return json.getAsJsonPrimitive().getAsDouble();
            } catch (Exception e) {
                return null;
            }
        }


    }

    public static class FloatTypeAdapter implements JsonDeserializer<Float> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.getAsJsonPrimitive().isJsonNull()) {
                return null;
            }
            try {
                return json.getAsJsonPrimitive().getAsFloat();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static class ShortTypeAdapter implements JsonDeserializer<Short> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Short deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.getAsJsonPrimitive().isJsonNull()) {
                return null;
            }
            try {
                return json.getAsJsonPrimitive().getAsShort();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static class BooleanTypeAdapter implements JsonDeserializer<Boolean> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.getAsJsonPrimitive().isJsonNull()) {
                return null;
            }
            try {
                return json.getAsJsonPrimitive().getAsBoolean();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static class JsonArrayAdapter implements JsonDeserializer<Object[]> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Object[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.getAsJsonPrimitive().isJsonNull()) {
                return null;
            }
            try {
                return context.deserialize(json, typeOfT);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static class JsonObjectAdapter implements JsonDeserializer<Object> {
        // json转为对象时调用,实现JsonDeserializer<>接口
        @Override
        public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.getAsJsonPrimitive().isJsonNull()) {
                return null;
            }
            try {
                return context.deserialize(json, typeOfT);
            } catch (Exception e) {
                return null;
            }
        }
    }

}
