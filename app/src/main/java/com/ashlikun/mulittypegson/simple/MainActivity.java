package com.ashlikun.mulittypegson.simple;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ashlikun.mulittypegson.simple.HomeData.TopBannerData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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

        HomeData data = new HomeData().parse(json, HomeData.class);
        List<TopBannerData> data1 = data.dataList.get(0).getArrayData();

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
