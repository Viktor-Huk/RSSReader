package com.example.rssreader.network;

import com.example.rssreader.model.Article;

public interface EventCallback {

    void call(Event event);
}
