package com.ashlikun.gson.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author　　: 李坤
 * 创建时间: 2022/4/28 22:58
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：int / Integer 类型解析适配器，参考：{@link com.google.gson.internal.bind.TypeAdapters#INTEGER}
 */

public class IntegerTypeAdapter extends TypeAdapter<Integer> {

    @Override
    public Integer read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case NUMBER:
                try {
                    return in.nextInt();
                } catch (NumberFormatException e) {
                    // 如果带小数点则会抛出这个异常
                    return (int) in.nextDouble();
                }
            case STRING:
                String result = in.nextString();
                if (result == null || "".equals(result)) {
                    return 0;
                }
                try {
                    return Integer.parseInt(result);
                } catch (NumberFormatException e) {
                    // 如果带小数点则会抛出这个异常
                    return (int) new BigDecimal(result).floatValue();
                }
            case NULL:
                in.nextNull();
                return null;
            default:
                in.skipValue();
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
        out.value(value);
    }
}