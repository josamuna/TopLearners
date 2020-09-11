package com.josamuna.toplearners.pojo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {
    private static Retrofit retrofit = null;
    public static ApiInterface getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://gadsapi.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getPostClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://docs.google.com/forms/d/e/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        // Creating an object for our Interface and return it
        return retrofit.create(ApiInterface.class);
    }
}
