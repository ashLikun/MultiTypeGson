package com.ashlikun.gson.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author　　: 李坤
 * 创建时间: 2022/4/28 22:57
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：float / Float 类型解析适配器，参考：{@link com.google.gson.internal.bind.TypeAdapters#FLOAT}
 */
public class FloatTypeAdapter extends TypeAdapter<Float> {

    @Override
    public Float read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case NUMBER:
                return (float) in.nextDouble();
            case STRING:
                String result = in.nextString();
                if (result == null || "".equals(result)) {
                    return 0F;
                }
                return Float.parseFloat(result);
            case NULL:
                in.nextNull();
                return null;
            default:
                in.skipValue();
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void write(JsonWriter out, Float value) throws IOException {
        out.value(value);
    }
}