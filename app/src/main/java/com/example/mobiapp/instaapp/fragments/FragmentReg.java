package com.example.mobiapp.instaapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.mobiapp.instaapp.MainActivity;
import com.example.mobiapp.instaapp.R;
import com.example.mobiapp.instaapp.data.AllInfo;
import com.example.mobiapp.instaapp.data.Prefs;
import com.example.mobiapp.instaapp.retrofit.App;
import com.example.mobiapp.instaapp.retrofit.classes.AllPosts;
import com.example.mobiapp.instaapp.retrofit.classes.Datum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mobi app on 04.09.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentReg extends Fragment{

    MainActivity activity;
    View v;
    WebView simpleWebView;
    AllInfo info ;
    Prefs prefs;
    Snackbar mSnackBar;
    RelativeLayout relMain;

    public FragmentReg(MainActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_reg, container, false);

        return v;

    }


    private void loadInsta(){
        activity.showProgress();
        init();
        simpleWebView.setWebViewClient(new MyWebViewClient());
        String url = "https://api.instagram.com/oauth/authorize/?client_id=aa694f2b73244ea2800aada37a0e77d3&redirect_uri=https://instagram.com/clubsotka&response_type=code";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url);

    }

    public void showSnackError(){
        Snackbar snackbar = Snackbar
                // с текстовой компоновкой "Вы изменили что-то"
                .make(relMain, "Ошибка соединения с сервером...", Snackbar.LENGTH_LONG)
                // и кнопкой "Вернуть как было?"
                .setAction("Повторить", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadInsta();
                    }
                });
        snackbar.show();
    }


    private void init() {

        relMain = (RelativeLayout) v.findViewById(R.id.relMain);
        simpleWebView = (WebView) v.findViewById(R.id.webView);
        info = new AllInfo(activity);
        prefs = new Prefs(activity);
    }

    private void saveToken(String url){
        simpleWebView.setVisibility(View.GONE);
        String[] urls = url.split("=");
        String code = urls[1];
        prefs.setCode(code);
        info.getToken();
        int i = 0;
    }

    public void createUser(){

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if(!url.contains("client_id")){
                saveToken(url);
            }else view.loadUrl(url);
            activity.hideProgress();
            return true;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        loadInsta();
    }
}
