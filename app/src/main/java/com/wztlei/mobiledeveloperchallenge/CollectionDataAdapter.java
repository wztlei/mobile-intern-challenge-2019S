package com.wztlei.mobiledeveloperchallenge;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class CollectionDataAdapter extends RecyclerView.Adapter<CollectionDataAdapter.CustomViewHolder> {


    private List<CustomCollection> dataList;

    CollectionDataAdapter(CustomCollectionList dataList){
        this.dataList = dataList.getCustomCollections();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {


        final View myView;

        TextView textTitle;
        TextView textBodyHtml;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            textTitle = myView.findViewById(R.id.text_title);
            textBodyHtml = myView.findViewById(R.id.text_body_html);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_collection_card, parent, false);
        return new CustomViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.textTitle.setText(dataList.get(position).getTitle());
        holder.textBodyHtml.setText(dataList.get(position).getBodyHtml());

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
