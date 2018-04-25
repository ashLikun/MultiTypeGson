package com.ashlikun.mulittypegson.simple;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ashlikun.mulittypegson.MultiTypeGsonBuilder;
import com.ashlikun.mulittypegson.simple.HomeData.*;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        json = getJson(this);
    }

    public static String getJson(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open("home.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void onButtonClick(View view) {
        GsonBuilder builder = new MultiTypeGsonBuilder()
                //指定要解析的字段名称
                .registerTypeElementName("type")
                //是否强制把外层的type字段的值设置给内部
                .forceUseUpperTypeValue()
                //注册外部解析类
                .registerTargetUpperLevelClass(DataListBean.class)
                //注册内部对应type解析类
                .registerTypeElementClass("top_banner", TopBannerData.class)
                .registerTypeElementClass("act_banner", ActBanner.class)
                .registerTypeElementClass("top_icons", TopIcons.class)
                .registerTypeElementClass("article", Article.class)
                .registerTypeElementClass("subject_area", SubjectArea.class)
                .registerTypeElementClass("recgoods", Recgoods.class)
                .registerTypeElementClass("baseInfo", HomeData.BaseInfo.class)
                .build();
        HomeData data = builder.create().fromJson(json, HomeData.class);
        TopBannerData data1 = data.dataList.get(0).getObjectData();
        Log.e("aaa", "");
    }
//    private String json = "{\n" +
//            "    \"total\": 2,\n" +
//            "    \"list\": [\n" +
//            "        {\n" +
//            "            \"type\": \"address\",\n" +
//            "            \"attributes\": {\n" +
//            "                \"street\": \"NanJing Road\",\n" +
//            "                \"city\": \"ShangHai\",\n" +
//            "                \"country\": \"China\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"type\": \"name\",\n" +
//            "            \"attributes\": {\n" +
//            "                \"first-name\": \"Su\",\n" +
//            "                \"last-name\": \"Tu\"\n" +
//            "            }\n" +
//            "        }\n" +
//            "    ]\n" +
//            "}";
}
