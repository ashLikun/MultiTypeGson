package com.ashlikun.gson.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * @author　　: 李坤
 * 创建时间: 2022/4/28 22:52
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：Array 解析适配器，参考：{@link com.google.gson.internal.bind.CollectionTypeAdapterFactory}
 */
public class CollectionTypeAdapterFactory implements TypeAdapterFactory {

    public ConstructorConstructor mConstructorConstructor;

    public CollectionTypeAdapterFactory(ConstructorConstructor constructor) {
        mConstructorConstructor = constructor;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        // 判断是否包含这种类型
        if (ReflectiveTypeUtils.containsClass(rawType)) {
            return null;
        }
        if (typeToken.getType() instanceof GenericArrayType ||
                typeToken.getType() instanceof Class &&
                        ((Class<?>) typeToken.getType()).isArray()) {
            return null;
        }

        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }

        Type elementType = $Gson$Types.getCollectionElementType(type, rawType);
        TypeAdapter<?> elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType));
        ObjectConstructor<T> constructor = mConstructorConstructor.get(typeToken);

        // create() doesn't define a type parameter
        CollectionTypeAdapter collectionTypeAdapter =
                new CollectionTypeAdapter(gson, elementType, elementTypeAdapter, constructor);
        collectionTypeAdapter.setReflectiveType(typeToken, null);
        return collectionTypeAdapter;
    }
}