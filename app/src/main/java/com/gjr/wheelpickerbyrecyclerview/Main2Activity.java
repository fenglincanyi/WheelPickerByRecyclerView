package com.gjr.wheelpickerbyrecyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gjr.wheelpickerbyrecyclerview.data.DateUtil;

import java.util.List;

import static com.gjr.wheelpickerbyrecyclerview.R.id.bottom_ll;
import static com.gjr.wheelpickerbyrecyclerview.R.id.btn;

public class Main2Activity extends AppCompatActivity {

    private WheelRecyclerView recyclerViewYear;
    private WheelRecyclerView recyclerViewYear1;
    private TextView resultTv;
    private Button btn;
    private List<String> yearData;
    private LinearLayoutManager linearLayoutManager;
    private List<String> yearData1;
    private LinearLayoutManager linearLayoutManager1;
    private YearAdapter yearAdapter;
    private YearAdapter yearAdapter1;
    private int currentYearPosition;
    private int lastYearPosition;
    private int lastYearPosition1;
    private int currentYearPosition1;
    private boolean isCreate = true;
    private boolean isCreate1;
    private LinearLayout bottom_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        bottom_ll = (LinearLayout) findViewById(R.id.bottom_ll);
        recyclerViewYear = (WheelRecyclerView) findViewById(R.id.recycler_year);
        recyclerViewYear1 = (WheelRecyclerView) findViewById(R.id.recycler_year1);
        resultTv = (TextView) findViewById(R.id.result_tv);
        btn = (Button) findViewById(R.id.btn);

//        recyclerViewYear = new WheelRecyclerView(this);
//        recyclerViewYear1 = new WheelRecyclerView(this);
//
//        bottom_ll.addView(recyclerViewYear);
//        bottom_ll.addView(recyclerViewYear1);
//        recyclerViewYear.setLayoutParams(new LinearLayout.LayoutParams(getScreenW(this)/2, 360));
//        recyclerViewYear1.setLayoutParams(new LinearLayout.LayoutParams(getScreenW(this)/2, 360));
    }
//
    private void initData() {
        yearData = DateUtil.init();// 模拟数据
        yearData1 = DateUtil.init();

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewYear.setLayoutManager(linearLayoutManager);
        yearAdapter = new YearAdapter(yearData);
        recyclerViewYear.setAdapter(yearAdapter);
//        recyclerViewYear.setInitPosition(lastYearPosition = 10);

        linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewYear1.setLayoutManager(linearLayoutManager1);
        yearAdapter1 = new YearAdapter(yearData1);
        recyclerViewYear1.setAdapter(yearAdapter1);
//        recyclerViewYear1.setInitPosition(lastYearPosition1 = 2);

        recyclerViewYear1.moveToPosition(10);
        // 现在默认的就是显示 第一条在中间
//        recyclerViewYear.scrollBy(0, -ScreenUtil.dip2px(this, 72f));
//        recyclerViewYear.smoothScrollBy(0, ScreenUtil.dip2px(this, 36f*10));
    }

    private void initListener() {
        recyclerViewYear.setScrollListener(new WheelRecyclerView.ScrollListener() {
            @Override
            public void scrollChanged(int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE) {
                    currentYearPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    System.out.println("currentYearPosition: "+currentYearPosition);
                    if (lastYearPosition != currentYearPosition) {
                        yearData1.remove(1);
                        yearAdapter1.notifyDataSetChanged();
                        lastYearPosition = currentYearPosition;
//                        recyclerViewYear1.moveToPosition(0);
                    }
                }
            }
        });
        recyclerViewYear1.setScrollListener(new WheelRecyclerView.ScrollListener() {
            @Override
            public void scrollChanged(int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE) {
                    currentYearPosition1 = linearLayoutManager.findFirstVisibleItemPosition();
                    System.out.println("currentYearPosition1: "+currentYearPosition1);
                    if (currentYearPosition1 >= 0) {
                    }
                }
            }
        });
    }
}
