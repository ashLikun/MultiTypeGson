package com.ashlikun.mulittypegson.simple

/**
 * 作者　　: 李坤
 * 创建时间: 2020/11/11　17:49
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：
 */
public class AdddKt {
    var type = ""
    var data: List<DataData>? = null
    var bool = 234f
    var number = ""
    val devices by lazy {
        "123"
    }

    public class DataData {
        var jump: JumpData? = null
    }

    public class JumpData {
        var params: Map<String, Any>? = null
    }
}