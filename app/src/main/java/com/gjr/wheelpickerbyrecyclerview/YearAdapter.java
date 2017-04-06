package com.gjr.wheelpickerbyrecyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gjr.wheelpickerbyrecyclerview.data.Year;

import java.util.List;

/**
 * Created by geng
 * on 2017/3/29.
 */
public class YearAdapter extends RecyclerView.Adapter<YearAdapter.VHolder> {

    private List<String> yearSource;

    public YearAdapter(List<String> data) {
        this.yearSource = data;
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(VHolder holder, int position) {
        holder.tv.setText(yearSource.get(position));
//        if (position %2==0) {
//            holder.tv.setBackgroundColor(Color.parseColor("#ff0000"));
//        } else {
//            holder.tv.setBackgroundColor(Color.parseColor("#00ff00"));
//        }
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
