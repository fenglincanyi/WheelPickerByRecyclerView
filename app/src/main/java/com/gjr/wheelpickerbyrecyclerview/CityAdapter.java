package com.gjr.wheelpickerbyrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gjr.wheelpickerbyrecyclerview.data.bean.City;
import com.gjr.wheelpickerbyrecyclerview.data.bean.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geng
 * on 2017/3/29.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.VHolder> {

    private List<City> yearSource;

    public CityAdapter(List<City> data) {
        yearSource = new ArrayList<>();
        yearSource.addAll(data);
    }

    public void setData(List<City> data) {
        if (yearSource != null) {
            yearSource.clear();
            yearSource.addAll(data);
        }
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(VHolder holder, int position) {
        holder.tv.setText(yearSource.get(position).cName);
    }

    @Override
    public int getItemCount() {
        return yearSource == null ? 0 : yearSource.size();
    }

    public static class VHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public VHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}
