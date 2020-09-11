package com.josamuna.toplearners.pojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("/api/hours")
    Call<List<PojoLearnerLeader>> getLearnerLeader(); // List because Json Started with JSONArray
    @GET("/api/skilliq")
    Call<List<PojoLearnerSkillLeader>> getLearnerSkillIqLeader(); // List because Json Started with JSONArray

    // Post request
//    @FormUrlEncoded
//    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse") // EndPoint
//    void sendInformation(@Field("entry.1877115667") String firstName,
//                         @Field("entry.2006916086") String lastName,
//                         @Field("entry.1824927963") String emailAddress,
//                         @Field("entry.284483984") String linkGitHub,
//                         Callback<PojoProjectSubmit> callback);

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse") // EndPoint
    Call<PojoProjectSubmit> sendInformations
            (@Field("firstName") String firstName,
             @Field("lastName") String lastName,
             @Field("emailAddress") String emailAddress,
             @Field("linkGitHub") String linkGitHub);
}
