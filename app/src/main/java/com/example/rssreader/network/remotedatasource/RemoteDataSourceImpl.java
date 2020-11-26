package com.example.rssreader.network.remotedatasource;

import com.example.rssreader.network.NetworkService;
import com.example.rssreader.network.RssApi;

public class RemoteDataSourceImpl implements RemoteDataSource {

    @Override
    public RssApi getRssApi() {
        return NetworkService.getInstance().getRssApi();
    }
}
