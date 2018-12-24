package com.ashlikun.gson;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/12/24　17:18
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：Gson 类型适配器
 * 保证json的String不会返回null
 */
public class StringNullAdapter extends TypeAdapter<String> {

    /**
     * json 转对象  反序列化
     */
    @Override
    public String read(JsonReader reader) throws IOException {
        try {
            JsonElement value = Streams.parse(reader);
            if (value.isJsonNull() || TextUtils.equals(value.getAsString().toUpperCase(), "NULL")) {
                return "";
            }
            return reader.nextString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 对象转json   序列化
     */
    @Override
    public void write(JsonWriter writer, String value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.value(value);
    }

    private static class NullStringToEmptyAdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            //匹配成功
            return (TypeAdapter<T>) new StringNullAdapter();
        }
    }

    /**
     * Returns a new factory that will match each type against {@code exactType}.
     */
    public static TypeAdapterFactory newFactory() {
        return new NullStringToEmptyAdapterFactory();
    }
}
