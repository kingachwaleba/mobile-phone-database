package com.example.mobilephonedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MobilePhoneListAdapter extends RecyclerView.Adapter<MobilePhoneListAdapter.MobilePhoneViewHolder> {

    private List<MobilePhone> mobilePhoneList;
    private LayoutInflater layoutInflater;

    public MobilePhoneListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.mobilePhoneList = null;
    }

    @Override
    public int getItemCount() {
        return mobilePhoneList != null ? mobilePhoneList.size() : 0;
    }

    public void setMobilePhoneList(List<MobilePhone> mobilePhoneList) {
        this.mobilePhoneList = mobilePhoneList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MobilePhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MobilePhoneViewHolder holder, int position) {

    }

    public class MobilePhoneViewHolder extends RecyclerView.ViewHolder {

        public MobilePhoneViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
