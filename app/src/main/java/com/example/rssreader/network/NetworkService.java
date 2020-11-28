package com.example.rssreader.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NetworkService {

    private static NetworkService instance;
    private RssApi rssApi;
    public static final ExecutorService networkExecutor = Executors.newSingleThreadExecutor();

    private NetworkService() {
        rssApi = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl("http://news.tut.by/")
                .build()
                .create(RssApi.class);
    }

    public RssApi getRssApi() {
        return rssApi;
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }
}
