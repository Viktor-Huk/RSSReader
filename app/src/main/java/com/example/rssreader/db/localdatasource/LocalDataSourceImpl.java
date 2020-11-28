package com.example.rssreader.db.localdatasource;

import com.example.rssreader.App;
import com.example.rssreader.db.RssRoomDatabase;
import com.example.rssreader.db.dao.RssDao;

public class LocalDataSourceImpl implements LocalDataSource {

    @Override
    public RssDao getRssDao() {
        return RssRoomDatabase.getDatabase().getRssDao();
    }
}
