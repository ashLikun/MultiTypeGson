package com.ashlikun.mulittypegson.simple;

import java.util.List;
import java.util.Map;

/**
 * 作者　　: 李坤
 * 创建时间: 2020/11/11　17:49
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */
class Addd {
    String type = "";
    List<DataData> data;
    float bool = 234;
    String number = "";
    String devices$delegate = "";

    class DataData {
        JumpData jump;
    }

    class JumpData {
        Map<String, Object> params;
    }
}
