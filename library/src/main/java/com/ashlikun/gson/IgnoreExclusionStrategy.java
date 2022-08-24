package com.ashlikun.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * 作者　　: 李坤
 * 创建时间: 2022/8/24　14:46
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：忽略字段的逻辑 by lazy
 */
public class IgnoreExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        if ("kotlin.Lazy".equals(clazz.getName())) {
            return true;
        }
        return false;
    }
}
