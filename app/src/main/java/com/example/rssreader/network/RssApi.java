package com.example.rssreader.network;

import com.example.rssreader.model.xml.Channel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RssApi {

    @GET("rss/index.rss")
    Call<Channel> getChannel();
}
