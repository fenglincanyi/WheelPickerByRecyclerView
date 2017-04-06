package com.gjr.wheelpickerbyrecyclerview.data;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by geng
 * on 2017/4/6.
 */
public class ProvinceUtil {

    public static void queryAll() {

    }

    public static void insertAll(final Context context) {
//        List<ProvinceBean> provinceBeanList = new ArrayList<>();
//        Gson gson = new Gson();
//        ProvinceBean provinceBean = null;
//        try {
//            String json = getJsonFromFile(context.getResources().getAssets().open("locations.json"));
//            JSONArray array = new JSONArray(json);
//            for (int i=0; i< array.length(); i++) {
//                provinceBean = gson.fromJson(array.get(i).toString(), ProvinceBean.class);
//                provinceBeanList.add(provinceBean);
//            }
//
//            // 插入
//
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    realm.createAllFromJson(ProvinceBean.class,context.getResources().getAssets().open("locations.json"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getJsonFromFile(InputStream in) {
        JSONObject jsonObject = null;
        StringBuilder result = new StringBuilder();
        String tmp;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while ((tmp = br.readLine()) != null) {
                result.append(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
