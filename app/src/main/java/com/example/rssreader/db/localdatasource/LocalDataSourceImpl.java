package com.example.rssreader.db.localdatasource;

import com.example.rssreader.db.DatabaseManager;
import com.example.rssreader.db.dao.RssDao;

public class LocalDataSourceImpl implements LocalDataSource {

    @Override
    public RssDao getRssDao() {
        return DatabaseManager.getDatabase().getRssDao();
    }
}
