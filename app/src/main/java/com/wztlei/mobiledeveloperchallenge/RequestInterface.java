package com.wztlei.mobiledeveloperchallenge;

import com.wztlei.mobiledeveloperchallenge.pojos.CollectList;
import com.wztlei.mobiledeveloperchallenge.pojos.CustomCollectionList;
import com.wztlei.mobiledeveloperchallenge.pojos.ProductList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RequestInterface {

    @GET
    Call<CustomCollectionList> getCustomCollections(@Url String url);

    @GET
    Call<CollectList> getCollects(@Url String url);

    @GET
    Call<ProductList> getProducts(@Url String url);
}
