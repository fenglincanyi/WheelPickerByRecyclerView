package com.gjr.wheelpickerbyrecyclerview.data;

import android.util.SparseArray;

/**
 * Created by geng
 * on 2017/3/30.
 */
public class Year {

    public String currentYear;

    public SparseArray<String> monthList;

    public Year(String currentYear, SparseArray<String> monthList) {
        this.currentYear = currentYear;
        this.monthList = monthList;
    }
}
