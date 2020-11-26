package com.example.rssreader.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rssreader.R;
import com.example.rssreader.model.Article;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    private ImageView newsImageView = itemView.findViewById(R.id.news_image);
    private TextView newsTitle = itemView.findViewById(R.id.news_title);
    private TextView newsPubDate = itemView.findViewById(R.id.news_time);

    public void bind(Article article) {
        Glide
                .with(newsImageView.getContext())
                .load(article.getThumbnailUri())
                .into(newsImageView);

        newsTitle.setText(article.getTitle());
        newsPubDate.setText(article.getPubDate());
    }
}
