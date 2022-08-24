package com.ashlikun.mulittypegson.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashlikun.mulittypegson.simple.R
import com.ashlikun.mulittypegson.simple.MainActivity
import com.ashlikun.mulittypegson.simple.Addd
import com.ashlikun.gson.GsonHelper
import com.ashlikun.mulittypegson.simple.AdddKt
import android.util.Log
import android.view.View
import android.content.Context
import java.lang.StringBuilder
import java.io.InputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var json: String? = null
    var aaaa = """{
	"code": 10200,
	"message": "ok",
	"data": {
		"ridingId": 1519598506757865474
	}
}"""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        long aaas = 1519598506757865474L;
        setContentView(R.layout.activity_main)
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
        val json = getJson(this, "json3.json")
        val addd: AdddKt? = GsonHelper.getGsonNotNull().fromJson(json, AdddKt::class.java)
        Log.e("aaaa  222 ", addd.toString())
        onButtonClick(null)
    }

    fun onButtonClick(view: View?) {
        val bbbbb = GsonHelper.getGsonNotNull().fromJson<Map<*, *>>(aaaa, MutableMap::class.java)
        Log.e("bbbbb", bbbbb.toString())
    }

    companion object {
        fun getJson(context: Context, name: String?): String {
            val sb = StringBuilder()
            try {
                val inputStream = context.assets.open(name!!)
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                inputStream.close()
                bufferedReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return sb.toString()
        }
    }
}