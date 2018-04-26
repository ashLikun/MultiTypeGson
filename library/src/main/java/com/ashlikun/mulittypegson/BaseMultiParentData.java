package com.ashlikun.mulittypegson;

import java.util.List;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/4/25 0025　下午 3:11
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：需要动态解析上一级类,的抽象
 */
public interface BaseMultiParentData {
    /**
     * @author　　: 李坤
     * 创建时间: 2018/4/25 0025 下午 3:36
     * 邮箱　　：496546144@qq.com
     * <p>
     * 方法功能：获取类型为数组的数据
     */
    public <T> List<T> getArrayData();


    /**
     * @author　　: 李坤
     * 创建时间: 2018/4/25 0025 下午 3:36
     * 邮箱　　：496546144@qq.com
     * <p>
     * 方法功能：获取类型为对象的数据
     */
    public <T> T getObjectData();
}
