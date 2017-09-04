package com.example.mobiapp.instaapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mobiapp.instaapp.MainActivity;
import com.example.mobiapp.instaapp.R;
import com.example.mobiapp.instaapp.adapter.MyPostsAdapter;
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
public class FragmentMyPost extends Fragment {

    MainActivity activity;
    View v;
    RecyclerView recyclerView;
    MyPostsAdapter adapter;
    ProgressBar progress;
    List<Datum> list;
    Prefs prefs;
    FragmentMyPost fragment;

    public FragmentMyPost(MainActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_myposts, container, false);


        return  v;
    }

    @Override
    public void onResume() {
        loadMyPost();
        super.onResume();

    }

    private void loadMyPost() {
        init();
        getAllPost();
    }

    private void init() {
        fragment = this;
        list = new ArrayList<Datum>();
        prefs = new Prefs(activity);
        progress = (ProgressBar) v.findViewById(R.id.my_posts_progress);
        recyclerView = (RecyclerView) v.findViewById(R.id.my_posts_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(llm);
        adapter = new MyPostsAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    public void getAllPost(){
        progress.setVisibility(View.VISIBLE);
        App.getApi().getAllPosts(prefs.getToken()).enqueue(new Callback<AllPosts>() {
            @Override
            public void onResponse(Call<AllPosts> call, Response<AllPosts> response) {
                AllPosts allPosts = response.body();
                list.clear();
                list = allPosts.getData();
                adapter = new MyPostsAdapter(fragment, list);
                recyclerView.setAdapter(adapter);
                int a = 0;
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AllPosts> call, Throwable t) {
                progress.setVisibility(View.GONE);
                showSnackError();
            }
        });

    }

    public void showSnackError(){
        Snackbar snackbar = Snackbar
                // с текстовой компоновкой "Вы изменили что-то"
                .make(recyclerView, "Ошибка соединения с сервером...", Snackbar.LENGTH_LONG)
                .setDuration(20000)
                // и кнопкой "Вернуть как было?"
                .setAction("Повторить", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // показываем сообщение "Все вернулось на свои места!"
                        getAllPost();
                    }
                });
        snackbar.show();
    }

    public void sendPhoto(){
        showSnackError();
    }

}
