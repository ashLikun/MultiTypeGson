package com.ashlikun.gson.element;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author　　: 李坤
 * 创建时间: 2022/4/28 22:52
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：反射字段捆绑类，参考：{@link com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField}
 */

public abstract class ReflectiveFieldBound {

    /**
     * 字段名称
     */
    private final String mFieldName;
    /**
     * 序列化标记
     */
    private final boolean mSerialized;
    /**
     * 反序列化标记
     */
    private final boolean mDeserialized;

    public ReflectiveFieldBound(String name, boolean serialized, boolean deserialized) {
        mFieldName = name;
        mSerialized = serialized;
        mDeserialized = deserialized;
    }

    public String getFieldName() {
        return mFieldName;
    }

    public boolean isDeserialized() {
        return mDeserialized;
    }

    public boolean isSerialized() {
        return mSerialized;
    }

    public abstract void write(JsonWriter writer, Object value) throws IOException, IllegalAccessException;

    public abstract void read(JsonReader reader, Object value) throws IOException, IllegalAccessException;

    public abstract boolean writeField(Object value) throws IOException, IllegalAccessException;
}