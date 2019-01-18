package com.wztlei.mobiledeveloperchallenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CollectionDataAdapter extends RecyclerView.Adapter<CollectionDataAdapter.ViewHolder> {
    // TODO: https://medium.com/@dds861/json-parsing-using-retrofit-and-recycleview-2300d9fdcf15

    @NonNull
    @Override
    public CollectionDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionDataAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_version;
        private TextView tv_api_level;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_version = (TextView)itemView.findViewById(R.id.tv_version);
            tv_api_level = (TextView)itemView.findViewById(R.id.tv_api_level);

        }
    }
}
