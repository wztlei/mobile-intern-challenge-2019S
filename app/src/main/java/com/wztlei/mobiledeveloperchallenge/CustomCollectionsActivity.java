package com.wztlei.mobiledeveloperchallenge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class CustomCollectionsActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://shopicruit.myshopify.com/admin/";
    private static final String TAG = "WL/CustomCollections";

    // https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
    // https://shopicruit.myshopify.com/admin/collects.json?collection_id=68424466488&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
    // https://shopicruit.myshopify.com/admin/products.json?ids=2759137027,2759143811&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
    
    // USEFUL RETROFIT TUTORIAL
    // https://www.androidauthority.com/retrofit-android-tutorial-906928/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_collections);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface service = retrofit.create(RequestInterface.class);

        Call<CustomCollectionList> call = service.getCollections();

        call.enqueue(new Callback<CustomCollectionList>() {

            @Override
            public void onResponse(@NonNull Call<CustomCollectionList> call, @NonNull Response<CustomCollectionList> response) {
                loadDataList(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CustomCollectionList> call, @NonNull Throwable throwable) {

            }
        });


    }

    private void loadDataList(CustomCollectionList collectionList) {

        RecyclerView myRecyclerView = findViewById(R.id.myRecyclerView);
        CollectionDataAdapter myAdapter = new CollectionDataAdapter(collectionList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CustomCollectionsActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(myAdapter);
    }

    public void viewCollectionDetails(View view) {
        Log.d(TAG, "onclick collection");
        Intent intent = new Intent(getApplicationContext(), CollectionDetailsActivity.class);
        startActivity(intent);
    }
}
