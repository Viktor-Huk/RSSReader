package com.example.rssreader.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rssreader.db.entity.ArticleEntity;

import java.util.List;

@Dao
public interface RssDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addAll(List<ArticleEntity> articles);

    @Query("SELECT * FROM article_table")
    List<ArticleEntity> getAll();
}
