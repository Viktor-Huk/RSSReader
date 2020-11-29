package com.example.rssreader.utils.mapper;

import com.example.rssreader.db.entity.ArticleEntity;
import com.example.rssreader.model.Article;

import java.util.List;

public interface MapperArticleEntitiesToArticle extends Mapper<List<ArticleEntity>, List<Article>> {
}
