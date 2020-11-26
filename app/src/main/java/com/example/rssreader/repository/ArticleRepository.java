package com.example.rssreader.repository;

import android.util.Log;

import com.example.rssreader.model.Article;
import com.example.rssreader.model.xml.ListArticles;
import com.example.rssreader.network.ArticleApi;
import com.example.rssreader.network.Event;
import com.example.rssreader.network.MainViewModelCallback;
import com.example.rssreader.network.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private NetworkService networkService;

    public ArticleRepository(NetworkService networkService) {
        this.networkService = networkService;
    }

    public void getArticles(MainViewModelCallback callback) {

        ArticleApi api = networkService.getArticleApi();

        Call<ListArticles> call = api.getArticleApi();
        call.enqueue(new Callback<ListArticles>() {

            @Override
            public void onResponse(Call<ListArticles> call, Response<ListArticles> response) {

                Log.i("TAG", "On response, thread: " + Thread.currentThread().getName());

                if (response.isSuccessful()) {
                    List<Article> list = new ArrayList();
                    ListArticles r = response.body();

                    for (int i = 0; i < r.getItemArticleList().size(); i++) {
                        String link = r.getItemArticleList().get(i).getLink();
                        String thumbnailUri = r.getItemArticleList().get(i).getEnclosure().getUrl();
                        String title = r.getItemArticleList().get(i).getTitle();
                        String pubDate = r.getItemArticleList().get(i).getPubDate();

                        Article article = new Article(1L, link, thumbnailUri, title, pubDate);
                        list.add(article);
                    }

                    callback.call(Event.success(list));

                    Log.i("TAG", "On response is successful: " + list.toString());
                } else {
                    Log.i("TAG", "On response is not successful");
                }
            }

            @Override
            public void onFailure(Call<ListArticles> call, Throwable t) {
                Log.i("TAG", "On failure: " + t);
            }
        });
    }
}
