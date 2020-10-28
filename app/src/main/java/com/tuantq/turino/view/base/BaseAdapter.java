package com.tuantq.turino.view.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.VH> {
    private ArrayList<T> mListData;
    private Context mContext;


    public BaseAdapter(ArrayList<T> mListData) {
        this.mListData = mListData;
    }

    protected abstract int getLayoutId();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return new VH(v);
    }


    @Override
    public int getItemCount() {
        return mListData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.VH holder, int position) {

    }

    public class VH extends RecyclerView.ViewHolder {
        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
