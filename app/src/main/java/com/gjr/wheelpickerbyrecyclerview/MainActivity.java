package com.gjr.wheelpickerbyrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gjr.wheelpickerbyrecyclerview.data.DateUtil;

public class MainActivity extends AppCompatActivity {

    private CircleRecyclerView recyclerViewYear;
    private CircleRecyclerView recyclerViewMonth;
    private TextView resultTv;
    private Button btn;

    private SparseArray<String> yearData;
    private SparseArray<String> monthData;
    private int currentYearPosition;
    private int currentMonthPosition;
    private boolean isCreate = true;
    private boolean isCreate1 = true;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager1;
    private MonthAdapter monthAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        recyclerViewYear = (CircleRecyclerView) findViewById(R.id.recycler_year);
        recyclerViewMonth = (CircleRecyclerView) findViewById(R.id.recycler_month);
        resultTv = (TextView) findViewById(R.id.result_tv);
        btn = (Button) findViewById(R.id.btn);
    }

    private void initData() {
//        yearData = DateUtil.init();// 模拟50条数据
//        monthData = DateUtil.init();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewYear.setLayoutManager(linearLayoutManager);
        recyclerViewYear.setViewMode(new ScaleYViewMode());
        recyclerViewYear.setNeedLoop(false);
        recyclerViewYear.setNeedCenterForce(true);
//        YearAdapter yearAdapter = new YearAdapter(yearData);
//        recyclerViewYear.setAdapter(yearAdapter);

        linearLayoutManager1 = new LinearLayoutManager(this);
        recyclerViewMonth.setLayoutManager(linearLayoutManager1);
        recyclerViewMonth.setViewMode(new ScaleYViewMode());
        recyclerViewMonth.setNeedLoop(false);
        recyclerViewMonth.setNeedCenterForce(true);
//        monthAdapter = new MonthAdapter(monthData);
//        recyclerViewMonth.setAdapter(monthAdapter);
    }

    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, String.format("选择：%s", yearData.get(currentYearPosition)), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewYear.setOnScrollListener(new CircleRecyclerView.OnScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
            }

            @Override
            public void onScrollStateChanged(int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE) {
                    currentYearPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    if (isCreate) {
                        recyclerViewYear.scrollToPosition(0);
                        isCreate = false;
                    }
                    if (currentYearPosition >= 0) {
                        resultTv.setText(String.format("选择：%s", yearData.get(currentYearPosition)));
//                        monthData.put(2,"fjsk");
//                        monthAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScrolled(int dx, int dy) {
            }
        });

        recyclerViewMonth.setOnScrollListener(new CircleRecyclerView.OnScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
            }

            @Override
            public void onScrollStateChanged(int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE) {
                    currentMonthPosition = linearLayoutManager1.findFirstVisibleItemPosition();
                    if (isCreate1) {
                        recyclerViewMonth.scrollToPosition(0);
                        isCreate1 = false;
                    }
                    if (currentMonthPosition >= 0) {
                        resultTv.setText(String.format("选择：%s", monthData.get(currentMonthPosition)));
                    }
                }
            }

            @Override
            public void onScrolled(int dx, int dy) {
            }
        });
    }

    public void test(View view) {
//        monthData.put(2, "fjsk");
//        monthAdapter.notifyDataSetChanged();
        startActivity(new Intent(this, Main2Activity.class));
    }
}
