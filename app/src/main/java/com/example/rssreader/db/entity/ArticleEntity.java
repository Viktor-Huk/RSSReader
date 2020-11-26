package com.example.rssreader.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
public class ArticleEntity {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "link")
    public String link;

    @ColumnInfo(name = "thumbnailUri")
    public String thumbnailUri;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "pubDate")
    public String pubDate;
}
