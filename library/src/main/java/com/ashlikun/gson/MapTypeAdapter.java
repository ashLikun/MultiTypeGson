package com.ashlikun.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 作者　　: 李坤
 * 创建时间: 2020/11/11　15:52
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：对象
 */
class MapTypeAdapter extends TypeAdapter<Object> {
    Gson gson;
    //MapTypeAdapterFactory  Gson的真实
    TypeAdapter adapter;


    public <T> MapTypeAdapter(Gson gson, TypeAdapter<T> delegateAdapter) {
        this.gson = gson;
        adapter = delegateAdapter;
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        switch (token) {
            case BEGIN_ARRAY:
                return gson.getAdapter(List.class).read(in);

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

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        adapter.write(out, value);
    }

    private static class MapAdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (!Map.class.isAssignableFrom(rawType)) {
                return null;
            }
            //匹配成功
            return (TypeAdapter<T>) new MapTypeAdapter(gson, gson.getDelegateAdapter(this, typeToken));
        }
    }

    /**
     * Returns a new factory that will match each type against {@code exactType}.
     */
    public static MapAdapterFactory newFactory() {
        return new MapTypeAdapter.MapAdapterFactory();
    }
}
