package com.wztlei.mobiledeveloperchallenge;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomCollectionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<CustomCollection> data;
    private CollectionDataAdapter adapter;
    private static final String TAG = "WL/CollectionsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_collections);

        initViews();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        // TODO: https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://shopicruit.myshopify.com/admin/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CollectionsRequestInterface request = retrofit.create(CollectionsRequestInterface.class);


        Call<CollectionsJSONResponse> call = request.getJSON();

        call.enqueue(new Callback<CollectionsJSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<CollectionsJSONResponse> call,
                                   @NonNull Response<CollectionsJSONResponse> response) {

                CollectionsJSONResponse jsonResponse = response.body();
                if (jsonResponse != null && jsonResponse.getCustomCollections() != null) {
                    data = new ArrayList<>(Arrays.asList(jsonResponse.getCustomCollections()));
                } else {
                    Log.d(TAG, "jsonResponse == null");
                }
                adapter = new CollectionDataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<CollectionsJSONResponse> call,
                                  @NonNull Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
