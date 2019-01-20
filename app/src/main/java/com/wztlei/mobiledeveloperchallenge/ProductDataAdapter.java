package com.wztlei.mobiledeveloperchallenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wztlei.mobiledeveloperchallenge.pojos.Product;
import com.wztlei.mobiledeveloperchallenge.pojos.ProductList;
import com.wztlei.mobiledeveloperchallenge.pojos.Variant;


import java.util.List;

public class ProductDataAdapter extends RecyclerView.Adapter<ProductDataAdapter.CustomViewHolder> {

    class CustomViewHolder extends RecyclerView.ViewHolder {
        final View myView;

        TextView textProductName;
        TextView textVendor;
        TextView textTotalInventory;
        TextView textCollectionTitle;
        ImageView imageCollection;
        ImageView imageProduct;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            textProductName = myView.findViewById(R.id.text_product_name);
            textVendor = myView.findViewById(R.id.text_vendor);
            textTotalInventory = myView.findViewById(R.id.text_total_inventory);
            textCollectionTitle = myView.findViewById(R.id.text_collection_title);
            imageCollection = myView.findViewById(R.id.image_collection);
            imageProduct = myView.findViewById(R.id.image_product);
        }
    }

    private List<Product> products;
    private String collectTitle;
    private String collectBodyHtml;
    private String collectImageSrc;
    private static final String TAG = "WL/CollectionAdapter";

    ProductDataAdapter(ProductList productList, String collectTitle, String collectBodyHtml, String collectImageSrc){
        this.products = productList.getProducts();
        this.collectTitle = collectTitle;
        this.collectBodyHtml = collectBodyHtml;
        this.collectImageSrc = collectImageSrc;
    }


    @NonNull
    @Override
    public ProductDataAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_product_card, parent, false);
        return new ProductDataAdapter.CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductDataAdapter.CustomViewHolder holder, int position) {
        Product product = products.get(position);
        //String vendorText = "Vendor: " + product.getVendor();
        String inventoryText = "Total Inventory: " + getInventoryCount(product);
        String productImageSrc = product.getImage().getSrc();


        holder.textProductName.setText(product.getTitle());
        holder.textCollectionTitle.setText(collectTitle);
        holder.textTotalInventory.setText(inventoryText);
        holder.textVendor.setText(product.getVendor());

        new ImageLoadTask(collectImageSrc, holder.imageCollection).execute();
        new ImageLoadTask(productImageSrc, holder.imageProduct).execute();
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    private int getInventoryCount(Product product) {
        List<Variant> variants = product.getVariants();
        int count = 0;

        for (Variant variant : variants) {
            count += variant.getInventoryQuantity();
        }

        return count;
    }

}
