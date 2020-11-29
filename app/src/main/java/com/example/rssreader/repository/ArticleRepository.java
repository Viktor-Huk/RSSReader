package com.example.rssreader.repository;

import android.util.Log;

import com.example.rssreader.db.RssRoomDatabase;
import com.example.rssreader.db.entity.ArticleEntity;
import com.example.rssreader.db.localdatasource.LocalDataSource;
import com.example.rssreader.model.Article;
import com.example.rssreader.model.xml.Channel;
import com.example.rssreader.network.Event;
import com.example.rssreader.network.EventCallback;
import com.example.rssreader.network.NetworkService;
import com.example.rssreader.network.remotedatasource.RemoteDataSource;
import com.example.rssreader.utils.mapper.MapperArticleEntitiesToArticle;
import com.example.rssreader.utils.mapper.MapperArticleEntitiesToArticleImpl;
import com.example.rssreader.utils.mapper.MapperChannelToList;
import com.example.rssreader.utils.mapper.MapperChannelToListImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static final String TAG = ArticleRepository.class.getSimpleName();
    private static ArticleRepository instance;
    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;
    private MapperChannelToList mapperChannelToList = new MapperChannelToListImpl();
    private MapperArticleEntitiesToArticle mapperArticleEntitiesToArticle = new MapperArticleEntitiesToArticleImpl();

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

    public void getData(EventCallback callback) {
        refreshData(callback);
    }

    private void getDataFromDatabase(EventCallback callback) {
        List<ArticleEntity> freshArticleEntities = localDataSource.getRssDao().getAll();

        if (freshArticleEntities == null || freshArticleEntities.isEmpty()) {
            callback.call(Event.error());
            Log.i(TAG, "Database is null or empty");
        } else {
            List<Article> articles = mapperArticleEntitiesToArticle.map(freshArticleEntities);

            callback.call(Event.success(articles));
        }
    }

    private void refreshData(EventCallback callback) {

        NetworkService.networkExecutor.execute(() -> {

            Log.i(TAG, "Refresh data thread: " + Thread.currentThread().getName());

            Call<Channel> call = remoteDataSource.getRssApi().getChannel();
            call.enqueue(new Callback<Channel>() {

                @Override
                public void onResponse(Call<Channel> call, Response<Channel> response) {

                    if (response.isSuccessful()) {
                        Channel channel = response.body();
                        List<ArticleEntity> list = mapperChannelToList.map(channel);

                        Log.i(TAG, "On response: " + list.toString());

                        RssRoomDatabase.databaseExecutor.execute(() -> {
                            localDataSource.getRssDao().addAll(list);
                            getDataFromDatabase(callback);
                        });

                    } else {
                        callback.call(Event.error());

                        RssRoomDatabase.databaseExecutor.execute(() -> {
                            getDataFromDatabase(callback);
                        });

                        Log.i(TAG, "On response is not successful");
                    }
                }

                @Override
                public void onFailure(Call<Channel> call, Throwable t) {
                    callback.call(Event.error());

                    RssRoomDatabase.databaseExecutor.execute(() -> {
                        getDataFromDatabase(callback);
                    });

                    Log.i(TAG, "On failure: " + t);
                }
            });
        });
    }
}
