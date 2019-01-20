package com.wztlei.mobiledeveloperchallenge;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wztlei.mobiledeveloperchallenge.pojos.CustomCollection;
import com.wztlei.mobiledeveloperchallenge.pojos.CustomCollectionList;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class CollectionDataAdapter extends RecyclerView.Adapter<CollectionDataAdapter.CustomViewHolder> {

    private List<CustomCollection> dataList;
    private static final String TAG = "WL/CollectionAdapter";

    CollectionDataAdapter(CustomCollectionList dataList){
        this.dataList = dataList.getCustomCollections();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        final View myView;

        TextView textTitle;
        TextView textBodyHtml;
        ImageView imageCollection;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            textTitle = myView.findViewById(R.id.text_title);
            //textBodyHtml = myView.findViewById(R.id.text_body_html);
            imageCollection = myView.findViewById(R.id.image_collection);
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
       // holder.textBodyHtml.setText(dataList.get(position).getBodyHtml());

        String imageSrc = dataList.get(position).getImage().getSrc();

        Log.d(TAG, imageSrc);

        new ImageLoadTask(imageSrc, holder.imageCollection).execute();

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
