package com.ashlikun.gson.data;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
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
    @NonNull
    public final static String NULL = "";

    /**
     * json 转对象  反序列化
     */
    @Override
    public String read(JsonReader reader) {
        try {
            JsonElement value = Streams.parse(reader);
            if (value.isJsonNull() || TextUtils.isEmpty(value.getAsString())
                    || TextUtils.equals(value.getAsString().toUpperCase(), "NULL")) {
                return NULL;
            }
            return value.getAsString();
        } catch (Exception e) {
            return NULL;
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
}