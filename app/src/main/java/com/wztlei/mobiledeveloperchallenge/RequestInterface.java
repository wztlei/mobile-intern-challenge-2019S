package com.wztlei.mobiledeveloperchallenge;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<CustomCollectionList> getCollections();
}
