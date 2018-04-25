package com.ashlikun.mulittypegson.simple;

import com.ashlikun.mulittypegson.BaseMultiData;
import com.ashlikun.mulittypegson.BaseMultiUpperLeveData;

import java.util.List;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/4/25 0025　下午 1:51
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */
public class HomeData {


    public int code;
    public String msg;
    public int currentPage;
    public int totalPage;
    public PopupBean popup;
    public List<TopMenusBean> top_menus;
    public List<DataListBean> dataList;

    public static class JumpBean {
        public String title;
        public String url;
        public String type;
        public String params;
    }

    public static class PopupBean {
        public String backimg;
        public String btnimg;
        public JumpBean jump;
    }

    public static class TopMenusBean {
        public String id;
        public String title;
        public String type;
        public String class_id;
        public String wechat_url;
        public JumpBean jump;
    }

    public static class DataListBean implements BaseMultiUpperLeveData {
        public String type;
        public String size;
        public int top;
        public List<BaseMultiData> data;
        public BaseMultiData objectData;


        @Override
        public <T> List<T> getArrayData() {
            return (List<T>) data;
        }

        @Override
        public <T> T getObjectData() {
            return (T) objectData;
        }
    }

    public static class TopBannerData implements BaseMultiData {

        public String img;
        public JumpBean jump;
    }

    public static class ActBanner implements BaseMultiData {

    }

    public static class TopIcons implements BaseMultiData {

        public String main_title;
        public String sub_title;
        public String img;
        public String img_btn_addr;
        public String area_id;
        public JumpBean jump;
    }

    public static class Article implements BaseMultiData {

        public String article_id;
        public String class_id;
        public String title;
        public String imgs_1;
    }

    public static class SubjectArea implements BaseMultiData {
        public String url;
        public String img;
        public String title;
        public JumpBean jump;
    }

    public static class HotArea implements BaseMultiData {

    }

    public static class BaseInfo implements BaseMultiData {

        public String goods_attr;
        public String defalut_image_id;
        public String goods_id;
        public String goods_title;
        public String goods_name;
        public String goods_des;
        public String Price;
        public String scPrice;
        public String wlPrice;
        public String jsPrice;
        public String fxPrice;
        public String goodsStock;
        public String goodsNowStock;
        public String description;
        public String type;
        public String view;
        public int sale;
        public String seller_id;
        public String is_show;
        public String is_onsale;
        public String base_sale;
        public String area_id;
        public String thumbnail_m;
        public String thumbnail_s;
        public String image_id;
        public String deliver_score;
    }

    public static class Recgoods implements BaseMultiData {
        public String goodsNowStock;
        public String goods_id;
        public String goods_name;
        public String goods_des;
        public String type;
        public String default_image;
        public String scPrice;
        public String Price;
        public String wlPrice;
        public String add_time;
        public String seller_id;
        public int sale;
        public String base_sale;
        public String spec_id;
        public String before_act_Price;
        public String has_storecoupon;
        public String maidian;
        public String good_rate;
        public String act_image;
    }
}
