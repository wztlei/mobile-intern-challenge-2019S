package com.wztlei.mobiledeveloperchallenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CollectionDataAdapter extends RecyclerView.Adapter<CollectionDataAdapter.ViewHolder> {

    private ArrayList<CustomCollection> customCollections;

    CollectionDataAdapter(ArrayList<CustomCollection> customCollections) {
        this.customCollections = customCollections;
    }

    @NonNull
    @Override
    public CollectionDataAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_collection_card, parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CollectionDataAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(customCollections.get(position).getTitle());
        holder.tvBodyHtml.setText(customCollections.get(position).getBodyHtml());
    }

    @Override
    public int getItemCount() {
        if (customCollections == null) {
            return -1;
        } else {
            return customCollections.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvBodyHtml;

        ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.text_title);
            tvBodyHtml = itemView.findViewById(R.id.text_body_html);

        }
    }
}
