package com.example.rssreader.network;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NetworkService {

    private static NetworkService instance;
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl("http://news.tut.by/")
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    public ArticleApi getArticleApi() {
        return retrofit.create(ArticleApi.class);
    }
}
