package com.josamuna.toplearners.pojo;

import retrofit.RestAdapter;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {
    private static Retrofit retrofit = null;
    public static ApiInterface getClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://gadsapi.herokuapp.com") // EndPoint
                .build(); // Build Api Adapter

        // Creating an object for our Interface and return it
        return restAdapter.create(ApiInterface.class);
    }

    @POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse/") // EndPoint
    public static ApiInterface getPostClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        // Creating an object for our Interface and return it
        return retrofit.create(ApiInterface.class);
    }
}
