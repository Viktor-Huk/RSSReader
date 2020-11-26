package com.example.rssreader.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rssreader.db.dao.RssDao;
import com.example.rssreader.db.entity.ArticleEntity;

@Database(entities = {ArticleEntity.class}, version = 1, exportSchema = false)
public abstract class RssRoomDatabase extends RoomDatabase {

    public abstract RssDao getRssDao();
}
