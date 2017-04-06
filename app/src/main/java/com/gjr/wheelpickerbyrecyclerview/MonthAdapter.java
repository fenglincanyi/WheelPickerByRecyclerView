package com.gjr.wheelpickerbyrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gjr.wheelpickerbyrecyclerview.data.Year;
import com.gjr.wheelpickerbyrecyclerview.data.bean.City;
import com.gjr.wheelpickerbyrecyclerview.data.bean.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geng
 * on 2017/3/29.
 */
public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.VHolder> {

    private List<String> monthSource;

    public MonthAdapter(List<String> data) {
        monthSource = new ArrayList<>();
        monthSource.addAll(data);
    }

    public void setData(List<String> data) {
        if (monthSource != null) {
            monthSource.clear();
            monthSource.addAll(data);
        }
    }

    @Override
    public MonthAdapter.VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MonthAdapter.VHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MonthAdapter.VHolder holder, int position) {
        holder.tv.setText(monthSource.get(position));
    }

    @Override
    public int getItemCount() {
        return monthSource == null ? 0 : monthSource.size();
    }

    public static class VHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public VHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}
