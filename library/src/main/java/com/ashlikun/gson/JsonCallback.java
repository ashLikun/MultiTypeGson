package com.ashlikun.gson;


import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;

/**
 * @author　　: 李坤
 * 创建时间: 2022/4/28 22:51
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：Json 解析异常监听器
 */

public interface JsonCallback {

    /**
     * 类型解析异常
     *
     * @param typeToken 类型 Token
     * @param fieldName 字段名称（可能为空）
     * @param jsonToken 后台给定的类型
     */
    void onTypeException(TypeToken<?> typeToken, String fieldName, JsonToken jsonToken);
}