package com.example.baselibrary.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<BEAN, VB extends ViewBinding> extends RecyclerView.Adapter<BaseAdapter.BaseHolder> {
    public List<BEAN> data = new ArrayList();


    @Override
    public void onBindViewHolder(@NonNull @NotNull BaseAdapter.BaseHolder holder, int position) {
        onBindHolder(holder, position, (VB) holder.binding, data.get(position));

    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull BaseAdapter.BaseHolder holder, int position, @NonNull @NotNull List<Object> payloads) {
        if (payloads == null || payloads.size() == 0) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            onBindHolder(holder, position, (VB) holder.binding, data.get(position), payloads);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public abstract BaseHolder<VB> onCreateViewHolder(ViewGroup parent, int viewType);//构造方法

    public abstract void onBindHolder(BaseAdapter.BaseHolder holder, int position, VB binding, BEAN bean);

    public abstract void onBindHolder(BaseAdapter.BaseHolder holder, int position, VB binding, BEAN bean, List<Object> payloads);

    public void refreshData(List<BEAN> beanList) {
        if (beanList == null || beanList.size() == 0) return;
        data.clear();
        notifyDataSetChanged();
        data.addAll(beanList);
        notifyDataSetChanged();
    }

    public void addData(List<BEAN> beanList) {
        if (beanList == null || beanList.size() == 0) return;
        int size = data.size();
        data.addAll(beanList);
        notifyItemRangeInserted(size, data.size());
    }

    public static class BaseHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {
        public VB binding;

        public BaseHolder(VB binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
