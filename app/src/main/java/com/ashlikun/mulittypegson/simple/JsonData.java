package com.ashlikun.mulittypegson.simple;

import java.util.List;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/6/28　13:21
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */
public class JsonData {

    public int code;
    public String msg;
    public InfoData info;
    public int currentPage;
    public int pageCount;
    public int pageOffset;
    public int currentRows;
    public List<InfoData> types;
    public List<ListData> list;

    public static class InfoData {
        public String id;
        public String name;
    }

    public static class ListData {
        public int id;
        public String title;
        public String desc;
        public int pt;
        public int type;
        public int use_type;
        public String nums;
        public String send_nums;
        public int progress;
        public int limit_user;
        public int limit_pt;
        public int is_goods;
        public TypeCashData type_cash;

        public static class TypeCashData {
            public String man;
            public String jian;
            public int cash;
        }
    }
}
