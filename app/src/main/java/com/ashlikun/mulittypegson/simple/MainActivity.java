package com.ashlikun.mulittypegson.simple;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ashlikun.gson.GsonHelper;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        json = getJson(this);
        HashMap<String, Object> map = new HashMap<>();
        map.put("aaa", "aaaaaaaa");
        map.put("bbb", 111);
        map.put("ccc", true);
        String aaa = GsonHelper.getBuilderNotNull().create().toJson(map);
        Log.e("aaaa", aaa);
        HashMap<String, Object> aMap = GsonHelper.getBuilderNotNull().create().fromJson(aaa, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        if(aMap != null) {
            Log.e("aaaa  222 ", aMap.toString());
        }
    }

    public static String getJson(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open("json2.json");
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
        JsonData data = GsonHelper.getGson().fromJson(json, JsonData.class);
        Log.e("aaa", data.toString());
    }
}
