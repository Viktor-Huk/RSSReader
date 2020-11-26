package com.example.rssreader.network;

import com.example.rssreader.model.xml.ListArticles;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleApi {

    @GET("rss/index.rss")
    Call<ListArticles> getArticleApi();
}
