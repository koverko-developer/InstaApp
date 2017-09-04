package com.example.mobiapp.instaapp.retrofit;

import com.example.mobiapp.instaapp.retrofit.classes.AllPosts;
import com.example.mobiapp.instaapp.retrofit.classes.ResponseCode;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mobi app on 04.09.2017.
 */

public interface InstaAPI {

    @FormUrlEncoded
    @POST("/oauth/access_token")
    Call<ResponseCode> getCode(@Field("client_id") String client_id,
                               @Field("client_secret") String client_secret,
                               @Field("grant_type") String grant_type,
                               @Field("redirect_uri") String redirect_uri,
                               @Field("code") String code);

    @GET("/v1/users/self/media/recent/?")
    Call<AllPosts> getAllPosts(@Query("access_token") String access_token);

}
