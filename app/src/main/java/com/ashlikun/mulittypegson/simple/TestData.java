package com.ashlikun.mulittypegson.simple;

import com.ashlikun.mulittypegson.BaseMultiData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/4/24 0024　下午 3:14
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */
public class TestData {

    /**
     * total : 2
     * list : [{"type":"address","attributes":{"street":"NanJing Road","city":"ShangHai","country":"China"}},{"type":"name","attributes":{"first-name":"Su","last-name":"Tu"}}]
     */

    public int total;
    public List<ListBean> list;


    public static class ListBean {
        /**
         * type : address
         * attributes : {"street":"NanJing Road","city":"ShangHai","country":"China"}
         */

        public String type;
        public BaseMultiData attributes;


        public static class AttributesAddressBean implements BaseMultiData {
            public String street;
            public String city;
            public String country;
        }


        public static class AttributesNameBean implements BaseMultiData {
            @SerializedName("first-name")
            public String firstName;
            @SerializedName("last-name")
            public String lastName;
        }
    }
}
