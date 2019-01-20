package com.wztlei.mobiledeveloperchallenge;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.wztlei.mobiledeveloperchallenge.pojos.Collect;
import com.wztlei.mobiledeveloperchallenge.pojos.CollectList;
import com.wztlei.mobiledeveloperchallenge.pojos.ProductList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CollectionDetailsActivity extends AppCompatActivity {


    private long collectId;
    private String collectTitle;
    private String collectBodyHtml;
    private String collectImageSrc;


    private static final String UNUSED_BASE_URL = "https://www.example.com/";
    private static final String TAG = "WL/CollectionDetails";
    private static final String COLLECTS_URL_PREFIX = "https://shopicruit.myshopify.com/admin/collects.json?collection_id=";
    private static final String COLLECT_URL_SUFFIX = "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    private static final String PRODUCTS_URL_PREFIX = "https://shopicruit.myshopify.com/admin/products.json?ids=";
    private static final String PRODUCTS_URL_SUFFIX = "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_details);

        Bundle intentBundle = getIntent().getExtras();

        if (intentBundle != null) {
            collectId = intentBundle.getLong(Globals.COLLECT_ID);
            collectTitle = intentBundle.getString(Globals.COLLECT_TITLE);
            collectBodyHtml = intentBundle.getString(Globals.COLLECT_BODY_HTML);
            collectImageSrc = intentBundle.getString(Globals.COLLECT_IMAGE_SRC);
        }


        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Collections Details");
        }

        getCollects();
    }

    private void getCollects() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UNUSED_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String collectsUrl = COLLECTS_URL_PREFIX + Long.toString(collectId) + COLLECT_URL_SUFFIX;
        RequestInterface service = retrofit.create(RequestInterface.class);
        Call<CollectList> call = service.getCollects(collectsUrl);

        call.enqueue(new Callback<CollectList>() {

            @Override
            public void onResponse(@NonNull Call<CollectList> call,
                                   @NonNull Response<CollectList> response) {

                CollectList collectList = response.body();
                StringBuilder productIds = new StringBuilder();

                if (collectList != null) {
                    List<Collect> collects = collectList.getCollects();
                    int collectListSize = collects.size();

                    for (int i = 0; i < collectListSize; i++) {
                        if (i > 0) {
                            productIds.append(",");
                        }

                        productIds.append(collects.get(i).getProductId());
                    }
                }

                String productUrl = PRODUCTS_URL_PREFIX + productIds + PRODUCTS_URL_SUFFIX;
                getProducts(productUrl);
            }

            @Override
            public void onFailure(@NonNull Call<CollectList> call, @NonNull Throwable t) {

            }
        });
    }

    private void getProducts(String productsUrl){
        Log.d(TAG, productsUrl);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UNUSED_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface service = retrofit.create(RequestInterface.class);
        Call<ProductList> call = service.getProducts(productsUrl);

        call.enqueue(new Callback<ProductList>() {

            @Override
            public void onResponse(@NonNull Call<ProductList> call,
                                   @NonNull Response<ProductList> response) {
                ProductList productList = response.body();
                displayProducts(productList);
            }

            @Override
            public void onFailure(@NonNull Call<ProductList> call, @NonNull Throwable t) {

            }
        });
    }

    private void displayProducts(ProductList productList) {
        RecyclerView myRecyclerView = findViewById(R.id.recycler_products);
        ProductDataAdapter myAdapter = new ProductDataAdapter(productList, collectTitle, collectBodyHtml, collectImageSrc);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(CollectionDetailsActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(myAdapter);
    }

}
