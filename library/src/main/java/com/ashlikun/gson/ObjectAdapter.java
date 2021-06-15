package com.ashlikun.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者　　: 李坤
 * 创建时间: 2020/11/11　18:18
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */
class ObjectAdapter extends TypeAdapter<Object> {
    Gson gson;

    public ObjectAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        TypeAdapter<Object> typeAdapter = (TypeAdapter<Object>) gson.getAdapter(value.getClass());
        if (typeAdapter instanceof ObjectTypeAdapter) {
            out.beginObject();
            out.endObject();
            return;
        }

        typeAdapter.write(out, value);
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        switch (token) {
            case BEGIN_ARRAY:
                List<Object> list = new ArrayList<Object>();
                in.beginArray();
                while (in.hasNext()) {
                    list.add(read(in));
                }
                in.endArray();
                return list;

            case BEGIN_OBJECT:
                Map<String, Object> map = new LinkedTreeMap<String, Object>();
                in.beginObject();
                while (in.hasNext()) {
                    map.put(in.nextName(), read(in));
                }
                in.endObject();
                return map;

            case STRING:
                return gson.getAdapter(String.class).read(in);

            case NUMBER:
                //改写数字的处理逻辑，将数字值分为整型与浮点型。
                double dbNum = in.nextDouble();
                // 数字超过long的最大值，返回Double
                if (dbNum > Long.MAX_VALUE) {
                    return dbNum;
                }
                // 判断数字是否为整数值
                long lngNum = (long) dbNum;
                if (dbNum == lngNum) {
                    // 数字未超过Int的最大值，返回Int
                    if (lngNum <= Integer.MAX_VALUE) {
                        return (int) lngNum;
                    }
                    return lngNum;
                } else {
                    return dbNum;
                }
            case BOOLEAN:
                return gson.getAdapter(Boolean.class).read(in);
            case NULL:
                in.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }
}