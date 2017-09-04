package com.example.mobiapp.instaapp.retrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mobi app on 04.09.2017.
 */

public class App extends Application {


    private static InstaAPI umoriliApi;
    private static MyAPI myAPI;
    private Retrofit retrofit, myRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        umoriliApi = retrofit.create(InstaAPI.class);

        myRetrofit = new Retrofit.Builder()
                .baseUrl("https://anika-cs.by/server/insta/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        myAPI = myRetrofit.create(MyAPI.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static InstaAPI getApi() {
        return umoriliApi;
    }

    public static MyAPI getMyApi() {
        return myAPI;
    }

}
