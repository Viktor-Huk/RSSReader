package com.example.rssreader.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rssreader.db.localdatasource.LocalDataSourceImpl;
import com.example.rssreader.model.Article;
import com.example.rssreader.network.remotedatasource.RemoteDataSourceImpl;
import com.example.rssreader.repository.ArticleRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private ArticleRepository articleRepository = ArticleRepository.getInstance(
            new LocalDataSourceImpl(),
            new RemoteDataSourceImpl()
    );

    private MutableLiveData<List<Article>> _articles = new MutableLiveData<>();
    private LiveData<List<Article>> articles = _articles;

    public LiveData<List<Article>> getArticles() {
        return articles;
    }

    void getFreshArticles() {
        articleRepository.getArticles(event -> {
            Log.i("TAG", "MainViewModel: " + event.getArticles().toString());
            _articles.setValue(event.getArticles());
        });
    }

    @Override
    protected void onCleared() {
        articleRepository.shutdownWorkedThread();
        super.onCleared();
    }
}
