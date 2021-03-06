package com.yunusseker.heroin.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<Data> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<Data> list =new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolderBase(parent, viewType);
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolderBase(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.onBindViewHolderBase(holder, position);
    }

    public abstract void onBindViewHolderBase(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    public Data get(int position) {
        return list.get(position);
    }

    public List<Data> getDataList() {
        return list;
    }

    public void setData(List<Data> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

}
