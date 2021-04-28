package com.example.mobilephonedatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        // Create a new row layout based on XML
        @SuppressLint("InflateParams") View row = layoutInflater.inflate(R.layout.list_line, null);

        // Return a new holder object
        return new MobilePhoneViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MobilePhoneViewHolder holder, int position) {
        holder.producerLabel.setText(mobilePhoneList.get(position).getProducer());
        holder.modelLabel.setText(mobilePhoneList.get(position).getModel());
    }

    public class MobilePhoneViewHolder extends RecyclerView.ViewHolder {

        private TextView producerLabel;
        private TextView modelLabel;

        public MobilePhoneViewHolder(@NonNull View itemView) {
            super(itemView);

            producerLabel = itemView.findViewById(R.id.phoneProducer);
            modelLabel = itemView.findViewById(R.id.phoneModel);
        }
    }
}
