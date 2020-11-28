package com.example.rssreader.utils;

import com.example.rssreader.db.entity.ArticleEntity;
import com.example.rssreader.model.xml.Channel;

import java.util.ArrayList;
import java.util.List;

public class ListMapperImpl implements ListMapper {

    @Override
    public List<ArticleEntity> map(Channel input) {
        List<ArticleEntity> list = new ArrayList();

        for (int i = 0; i < input.getItemArticleList().size(); i++) {
            String link = input.getItemArticleList().get(i).getLink();
            String thumbnailUri = input.getItemArticleList().get(i).getEnclosure().getUrl();
            String title = input.getItemArticleList().get(i).getTitle();
            String pubDate = input.getItemArticleList().get(i).getPubDate();

            ArticleEntity article = new ArticleEntity(link, thumbnailUri, title, pubDate);
            list.add(article);
        }

        return list;
    }
}
