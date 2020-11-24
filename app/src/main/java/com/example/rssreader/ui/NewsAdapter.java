package com.example.rssreader.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.rssreader.R;
import com.example.rssreader.model.News;

public class NewsAdapter extends ListAdapter<News, NewsViewHolder> {

    protected NewsAdapter() {
        super(getNewsDiffUtilCallback());
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        News news = getItem(position);
        holder.bind(news);
    }

    private static DiffUtil.ItemCallback<News> getNewsDiffUtilCallback() {
        return new DiffUtil.ItemCallback<News>() {
            @Override
            public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.equals(newItem);
            }
        };
    }
}
