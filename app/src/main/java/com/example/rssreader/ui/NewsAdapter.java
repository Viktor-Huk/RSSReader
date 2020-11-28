package com.example.rssreader.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.rssreader.R;
import com.example.rssreader.model.Article;

public class NewsAdapter extends ListAdapter<Article, NewsViewHolder> {

    private OnArticleClickListener onArticleClickListener;

    protected NewsAdapter(OnArticleClickListener onArticleClickListener) {
        super(getNewsDiffUtilCallback());
        this.onArticleClickListener = onArticleClickListener;
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

        Article article = getItem(position);
        holder.bind(article);

        holder.itemView.setOnClickListener(v -> onArticleClickListener.onClick(article));
    }

    private static DiffUtil.ItemCallback<Article> getNewsDiffUtilCallback() {
        return new DiffUtil.ItemCallback<Article>() {
            @Override
            public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                return oldItem.getLink().equals(newItem.getLink());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                return oldItem.equals(newItem);
            }
        };
    }
}
