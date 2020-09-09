package com.josamuna.toplearners.pojo;

import retrofit.RestAdapter;

public class ApiAdapter {
    public static ApiInterface getClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://gadsapi.herokuapp.com") // EndPoint
                .build(); // Build Api Adapter

        // Creating an object for our Interface and return it
        return restAdapter.create(ApiInterface.class);
    }
}
