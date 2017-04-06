package com.gjr.wheelpickerbyrecyclerview.data;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by geng
 * on 2017/3/30.
 */
public class DateUtil {

    /**
     * 生成 x年x月
     *
     * @param delta xx年前——现在的日期
     */
    public static SparseArray<String> create(int delta) {
        Calendar calendar = Calendar.getInstance();
        SparseArray<String> monthList = new SparseArray<>();
        SparseArray<String> lastMonthList = new SparseArray<>();
//        SparseArray<Year> date = new SparseArray<>();
        int tmp = calendar.get(Calendar.MONTH) + 1;

        int i;
        for (i = 1; i <= 12; i++) {
            monthList.put(i - 1, String.format("%s月", i));
        }
//        for (i = 1; i <= tmp; i++) {
//            lastMonthList.put(i - 1, String.format("%s月", i));
//        }

//        tmp = calendar.get(Calendar.YEAR);
//        int tmp1 = tmp - delta;
//
//        int count = 0;
//        for (i = tmp1; i < tmp; i++) {
//            date.put(count++, new Year(String.format("%s年", i), monthList));
//        }
//        date.put(count, new Year(String.format("%s年", tmp), lastMonthList));

        return monthList;
    }


    public static List<String> init() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item" + i);
        }
        return list;
    }
}
