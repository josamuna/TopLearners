package com.josamuna.toplearners.pojo;

import com.josamuna.toplearners.pojo.PojoLearnerLeader;
import com.josamuna.toplearners.pojo.PojoLearnerSkillLeader;
import com.josamuna.toplearners.pojo.PojoProjectSubmit;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit2.Call;

public interface ApiInterface {
    @GET("/api/hours")
    void getLearnerLeader(Callback<List<PojoLearnerLeader>> callback); // List because Json Started with JSONArray

    @GET("/api/skilliq")
    void getLearnerSkillIqLeader(Callback<List<PojoLearnerSkillLeader>> callback); // List because Json Started with JSONArray

    // Post request
    @FormUrlEncoded
    @POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse") // EndPoint
    void sendInformation(@Field("entry.1877115667") String firstName,
                         @Field("entry.2006916086") String lastName,
                         @Field("entry.1824927963") String emailAddress,
                         @Field("entry.284483984") String linkGitHub,
                         Callback<PojoProjectSubmit> callback);

    @POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse") // EndPoint
    Call<PojoProjectSubmit> sendInformations
            (@Field("entry.1877115667") String firstName,
             @Field("entry.2006916086") String lastName,
             @Field("entry.1824927963") String emailAddress,
             @Field("entry.284483984") String linkGitHub);
}
