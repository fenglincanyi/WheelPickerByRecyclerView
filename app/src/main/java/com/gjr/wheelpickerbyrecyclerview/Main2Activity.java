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
import com.gjr.wheelpickerbyrecyclerview.data.ProvinceBean;
import com.gjr.wheelpickerbyrecyclerview.data.bean.City;
import com.gjr.wheelpickerbyrecyclerview.data.bean.Province;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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
    private LinearLayoutManager linearLayoutManager2;
    private YearAdapter yearAdapter;
    private CityAdapter yearAdapter1;
    private MonthAdapter yearAdapter2;
    private int currentYearPosition;
    private int lastYearPosition;
    private int lastYearPosition1;
    private int currentYearPosition1;
    private boolean isCreate = true;
    private boolean isCreate1;
    private LinearLayout bottom_ll;
    private Realm realm;
    private ArrayList<Province> provinceList;
    private WheelRecyclerView recycler_area;
    private int currentYearPosition2;

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
        recycler_area = (WheelRecyclerView) findViewById(R.id.recycler_area);
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

    private void initData() {
//        yearData = DateUtil.init();// 模拟数据
//        yearData1 = DateUtil.init();
        realm = Realm.getDefaultInstance();
//        allList = realm.where(ProvinceBean.class);


        provinceList = new ArrayList<>();


        RealmResults<ProvinceBean> pResults = realm.where(ProvinceBean.class).distinct("l1Name");
        ;
        int size1 = pResults.size();
        for (int i = 0; i < size1; i++) {
            Province p = new Province();
            p.pName = pResults.get(i).getL1Name();

            RealmResults<ProvinceBean> cResults = realm.where(ProvinceBean.class).equalTo("l1Name", p.pName).distinct("l2Name");
            int size2 = cResults.size();
            for (int j = 0; j < size2; j++) {
                City c = new City();
                c.cName = cResults.get(j).getL2Name();

                RealmResults<ProvinceBean> aResults = realm.where(ProvinceBean.class).equalTo("l2Name", c.cName).distinct("l3Name");
                int size3 = aResults.size();
                for (int k = 0; k < size3; k++) {
                    c.areas.add(aResults.get(k).getL3Name());
                }

                p.citys.add(c);
            }

            provinceList.add(p);
        }


//        pList = new ArrayList<>();
//        for (ProvinceBean p : realmResults) {
//            pList.add(p.getL1Name());
//        }


//        realmResults = realm.where(ProvinceBean.class).distinct("l2Name");
//        cList = new ArrayList<>();
//        for (ProvinceBean p:realmResults) {
//            cList.add(p.getL2Name());
//        }

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewYear.setLayoutManager(linearLayoutManager);
        yearAdapter = new YearAdapter(provinceList);
        recyclerViewYear.setAdapter(yearAdapter);

        linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewYear1.setLayoutManager(linearLayoutManager1);
        yearAdapter1 = new CityAdapter(provinceList.get(0).citys);
        recyclerViewYear1.setAdapter(yearAdapter1);


        linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_area.setLayoutManager(linearLayoutManager2);
        yearAdapter2 = new MonthAdapter(provinceList.get(0).citys.get(0).areas);
        recycler_area.setAdapter(yearAdapter2);
//        recyclerViewYear1.moveToPosition(10);
    }

    private void initListener() {
        recyclerViewYear.setScrollListener(new WheelRecyclerView.ScrollListener() {
            @Override
            public void scrollChanged(int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE) {
                    currentYearPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    if (lastYearPosition != currentYearPosition) {
                        yearAdapter1.setData(provinceList.get(currentYearPosition).citys);
                        yearAdapter1.notifyDataSetChanged();
                        lastYearPosition = currentYearPosition;
                        recyclerViewYear1.moveToPosition(0);

                        yearAdapter2.setData(provinceList.get(currentYearPosition).citys.get(0).areas);
                        yearAdapter2.notifyDataSetChanged();
                        lastYearPosition1 = currentYearPosition1;
                        recycler_area.moveToPosition(0);
                    }
                }
            }
        });
        recyclerViewYear1.setScrollListener(new WheelRecyclerView.ScrollListener() {
            @Override
            public void scrollChanged(int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE) {
                    currentYearPosition1 = linearLayoutManager1.findFirstVisibleItemPosition();
                    if (lastYearPosition1 != currentYearPosition1) {
                        yearAdapter2.setData(provinceList.get(currentYearPosition).citys.get(currentYearPosition1).areas);
                        yearAdapter2.notifyDataSetChanged();
                        lastYearPosition1 = currentYearPosition1;
                        recycler_area.moveToPosition(0);
                    }
                }
            }
        });
        recycler_area.setScrollListener(new WheelRecyclerView.ScrollListener() {
            @Override
            public void scrollChanged(int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE) {
                    currentYearPosition2 = linearLayoutManager.findFirstVisibleItemPosition();
                }
            }
        });
    }
}
