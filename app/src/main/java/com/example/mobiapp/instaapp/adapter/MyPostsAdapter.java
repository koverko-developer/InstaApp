package com.example.mobiapp.instaapp.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiapp.instaapp.R;
import com.example.mobiapp.instaapp.fragments.FragmentMyPost;
import com.example.mobiapp.instaapp.retrofit.classes.Datum;

import java.util.List;

/**
 * Created by mobi app on 04.09.2017.
 */


public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.ViewHolder> implements View.OnClickListener {

    FragmentMyPost fragmentMyPost;
    List<Datum> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView col_likes, col_comments;
        public ImageView img, img_likes, img_comments;
        public Button button;

        public ViewHolder(View v) {
            super(v);
            col_likes = (TextView) v.findViewById(R.id.myphoto_col_like);
            col_comments = (TextView) v.findViewById(R.id.myphoto_col_comments);
            img = (ImageView) v.findViewById(R.id.myphoto_img);
            img_likes = (ImageView) v.findViewById(R.id.myphoto_img_like);
            img_comments = (ImageView) v.findViewById(R.id.myphoto_img_comments);
            button = (Button) v.findViewById(R.id.myphoto_button_send);


        }
    }

    // Конструктор
    public MyPostsAdapter(FragmentMyPost fragmentMyPost, List<Datum> list) {
        this.fragmentMyPost = fragmentMyPost;
        this.list = list;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public MyPostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_photos, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        //ViewHolder vh = new ViewHolder(v);
        return new ViewHolder(v);
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            Datum datum = list.get(position);
            holder.col_likes.setText(String.valueOf(datum.getLikes().getCount()));
            holder.col_comments.setText(String.valueOf(datum.getComments().getCount()));
            Glide.with(holder.img.getContext()).load(datum.getImages().getStandardResolution().getUrl()).into(holder.img);
            holder.button.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.myphoto_button_send:
                fragmentMyPost.sendPhoto();
                break;
        }
    }



}
