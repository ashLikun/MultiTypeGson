package com.ashlikun.mulittypegson.simple;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ashlikun.gson.GsonHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String json;
    String aaaa = "{\n" +
            "\t\"code\": 10200,\n" +
            "\t\"message\": \"ok\",\n" +
            "\t\"data\": {\n" +
            "\t\t\"ridingId\": 1519598506757865474\n" +
            "\t}\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long aaas = 1519598506757865474L;
        setContentView(R.layout.activity_main);
//        json = getJson(this, "json2.json");
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("aaa", "aaaaaaaa");
//        map.put("bbb", 111);
//        map.put("ccc", true);
//        String aaa = GsonHelper.getBuilderNotNull().create().toJson(map);
//        Log.e("aaaa", aaa);
//        HashMap<String, Object> aMap = GsonHelper.getBuilderNotNull().create().fromJson(aaa, new TypeToken<HashMap<String, Object>>() {
//        }.getType());
//        if (aMap != null) {
//            Log.e("aaaa  222 ", aMap.toString());
//        }
//        json = getJson(this, "json3.json");
//        Addd addd = GsonHelper.getBuilderNotNull().create().fromJson(json, Addd.class);
//        Log.e("aaaa  222 ", addd.toString());

    }

    public static String getJson(Context context, String name) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open(name);
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
        Map bbbbb = GsonHelper.getBuilderNotNull().create().fromJson(aaaa, Map.class);
        Log.e("bbbbb", bbbbb.toString());
    }
}
