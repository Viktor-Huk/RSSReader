package com.example.rssreader.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rssreader.db.localdatasource.LocalDataSourceImpl;
import com.example.rssreader.model.Article;
import com.example.rssreader.network.remotedatasource.RemoteDataSourceImpl;
import com.example.rssreader.repository.ArticleRepository;

import java.util.List;

public class ListViewModel extends ViewModel {

    private static final String TAG = ListViewModel.class.getSimpleName();

    private ArticleRepository articleRepository = ArticleRepository.getInstance(
            new LocalDataSourceImpl(),
            new RemoteDataSourceImpl()
    );

    private MutableLiveData<List<Article>> _articles = new MutableLiveData<>();
    private LiveData<List<Article>> articles = _articles;

    private MutableLiveData<Boolean> _errorState = new MutableLiveData<>();
    private LiveData<Boolean> errorState = _errorState;

    public LiveData<Boolean> getErrorState() {
        return errorState;
    }

    public LiveData<List<Article>> getArticles() {
        return articles;
    }

    void getFreshArticles() {
        _errorState.setValue(false);

        articleRepository.getData(event -> {

            switch (event.getStatus()) {
                case SUCCESS: {
                    _errorState.postValue(false);
                    _articles.postValue(event.getData());
                }
                break;
                case ERROR: {
                    _errorState.postValue(true);
                }
                break;
            }
        });
    }
}