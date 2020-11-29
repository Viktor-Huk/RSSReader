package com.example.rssreader.utils.mapper;

import com.example.rssreader.db.entity.ArticleEntity;
import com.example.rssreader.model.Article;
import com.example.rssreader.utils.formatter.DateFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MapperArticleEntitiesToArticleImpl implements MapperArticleEntitiesToArticle {

    private final static String TAG = MapperArticleEntitiesToArticle.class.getSimpleName();

    @Override
    public List<Article> map(List<ArticleEntity> input) {

        List<Article> articleList = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {

            String link = input.get(i).getLink();
            String thumbnailUri = input.get(i).getThumbnailUri();
            String title = input.get(i).getTitle();

            Date date = DateFormatter.formatStringToDate(input.get(i).getPubDate());

            articleList.add(new Article(link, thumbnailUri, title, date));
        }

        Set<Article> articleSet = new TreeSet<>(articleList);
        articleList = new ArrayList<>(articleSet);

        return articleList;
    }
}
