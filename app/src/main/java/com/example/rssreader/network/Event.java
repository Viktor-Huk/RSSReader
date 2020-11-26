package com.example.rssreader.network;

import java.util.List;

public class Event <Article> {

    private Status status;
    private List<Article> articles;

    private Event(Status status, List<Article> articles) {
        this.status = status;
        this.articles = articles;
    }

    public static Event loading() {
        return new Event(Status.LOADING, null);
    }

    public static <Article> Event success(List<Article> data) {
        return new Event(Status.SUCCESS, data);
    }

    public static <Article> Event error(List<Article> data) {
        return new Event(Status.ERROR, data);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
