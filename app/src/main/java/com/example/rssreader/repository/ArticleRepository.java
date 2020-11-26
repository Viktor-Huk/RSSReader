package com.example.rssreader.repository;

import android.util.Log;

import com.example.rssreader.db.localdatasource.LocalDataSource;
import com.example.rssreader.model.Article;
import com.example.rssreader.model.xml.Channel;
import com.example.rssreader.network.Event;
import com.example.rssreader.network.MainViewModelCallback;
import com.example.rssreader.network.remotedatasource.RemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static ArticleRepository instance;
    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;

    private ExecutorService executer = Executors.newSingleThreadExecutor();

    private ArticleRepository(
            LocalDataSource localDataSource,
            RemoteDataSource remoteDataSource
    ) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static ArticleRepository getInstance(
            LocalDataSource localDataSource,
            RemoteDataSource remoteDataSource
    ) {
        if (instance == null) {
            instance = new ArticleRepository(localDataSource, remoteDataSource);
        }
        return instance;
    }

    public void getArticles(MainViewModelCallback callback) {

        Runnable task = () -> {

            Log.i("TAG", "Runnable, thread: " + Thread.currentThread().getName());

            Call<Channel> call = remoteDataSource.getRssApi().getChannel();
            call.enqueue(new Callback<Channel>() {

                @Override
                public void onResponse(Call<Channel> call, Response<Channel> response) {

                    Log.i("TAG", "On response, thread: " + Thread.currentThread().getName());

                    if (response.isSuccessful()) {
                        List<Article> list = new ArrayList();
                        Channel r = response.body();

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
                public void onFailure(Call<Channel> call, Throwable t) {
                    Log.i("TAG", "On failure: " + t);
                }
            });
        };

        executer.submit(task);
    }

    private void refreshData() {

    }

    public void shutdownWorkedThread() {
        executer.shutdown();
    }
}
