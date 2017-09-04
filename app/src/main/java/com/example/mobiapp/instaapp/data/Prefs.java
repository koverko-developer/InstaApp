package com.example.mobiapp.instaapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mobiapp.instaapp.MainActivity;

/**
 * Created by mobi app on 04.09.2017.
 */

public class Prefs {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Token = "token";
    public static final String Code = "code";

    SharedPreferences sharedpreferences;
    MainActivity activity;
    public Prefs(MainActivity activity){
        this.activity = activity;
        sharedpreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void setToken(String token){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Token, token);
        editor.commit();
    }

    public String getToken(){

        return sharedpreferences.getString(Token,"");
    }

    public String getCode(){
        return sharedpreferences.getString(Code,"");
    }

    public void setCode(String code){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Code, code);
        editor.commit();
    }
}