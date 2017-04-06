package com.gjr.wheelpickerbyrecyclerview;

import android.support.v7.widget.RecyclerView;
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
public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.ViewHoler> {

    private List<String> monthSource;

    public MonthAdapter(List<String> data) {
        this.monthSource = data;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.textView.setText(monthSource.get(position));
    }

    @Override
    public int getItemCount() {
        return monthSource == null ? 0 : monthSource.size();
    }

    public static class ViewHoler extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHoler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
