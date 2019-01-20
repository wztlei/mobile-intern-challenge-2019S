package com.wztlei.mobiledeveloperchallenge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wztlei.mobiledeveloperchallenge.pojos.CustomCollection;
import com.wztlei.mobiledeveloperchallenge.pojos.CustomCollectionList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomCollectionsActivity extends AppCompatActivity {

    private CustomCollectionList customCollectionList;

    private static final String UNUSED_BASE_URL = "https://www.example.com/";
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

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Custom Collections");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UNUSED_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface service = retrofit.create(RequestInterface.class);

        Call<CustomCollectionList> call = service.getCustomCollections(
                "https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6");

        call.enqueue(new Callback<CustomCollectionList>() {

            @Override
            public void onResponse(@NonNull Call<CustomCollectionList> call,
                                   @NonNull Response<CustomCollectionList> response) {
                customCollectionList = response.body();
                displayCollections(customCollectionList);
            }

            @Override
            public void onFailure(@NonNull Call<CustomCollectionList> call,
                                  @NonNull Throwable throwable) {

            }
        });
    }

    private void displayCollections(CustomCollectionList collectionList) {
        RecyclerView myRecyclerView = findViewById(R.id.recycler_collections);
        CollectionDataAdapter myAdapter = new CollectionDataAdapter(collectionList);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(CustomCollectionsActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(myAdapter);
    }

    public void onClickCollectionCard(View view) {
        RecyclerView myRecyclerView = findViewById(R.id.recycler_collections);
        int i = myRecyclerView.getChildLayoutPosition(view);
        CustomCollection customCollection = customCollectionList.getCustomCollections().get(i);

        long collectionId = customCollection.getId();
        String title = customCollection.getTitle();
        String bodyHtml = customCollection.getBodyHtml();
        String imageSrc = customCollection.getImage().getSrc();

        Log.d(TAG, "collectionId=" + collectionId);

        Intent intent = new Intent(getApplicationContext(), CollectionDetailsActivity.class);
        intent.putExtra(Globals.COLLECT_ID, collectionId);
        intent.putExtra(Globals.COLLECT_TITLE, title);
        intent.putExtra(Globals.COLLECT_BODY_HTML, bodyHtml);
        intent.putExtra(Globals.COLLECT_IMAGE_SRC, imageSrc);

        startActivity(intent);
    }


}
