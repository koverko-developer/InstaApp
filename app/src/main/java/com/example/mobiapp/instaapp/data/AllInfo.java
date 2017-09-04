package com.example.mobiapp.instaapp.data;

import com.example.mobiapp.instaapp.MainActivity;
import com.example.mobiapp.instaapp.retrofit.App;
import com.example.mobiapp.instaapp.retrofit.classes.ResponseCode;
import com.example.mobiapp.instaapp.retrofit.classes.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mobi app on 04.09.2017.
 */

public class AllInfo {

    MainActivity activity;
    Prefs prefs;

    public AllInfo(MainActivity activity){
        this.activity = activity;
        prefs = new Prefs(activity);
    }

    public void getToken(){


        App.getApi().getCode("aa694f2b73244ea2800aada37a0e77d3",
                "ea7ee627aa0141c482e15e5a1467fb6b",
                "authorization_code",
                "https://instagram.com/clubsotka",
                prefs.getCode()).enqueue(new Callback<ResponseCode>() {
            @Override
            public void onResponse(Call<ResponseCode> call, Response<ResponseCode> response) {
                ResponseCode code = response.body();
                String tokens = code.getAccess_token();
                User user = code.getUser();
                int a = 0;
                prefs.setToken(tokens);
                activity.showFragmentMyPost();
            }

            @Override
            public void onFailure(Call<ResponseCode> call, Throwable t) {
                activity.showFragmentReg();
            }
        });

    }



}
