package com.example.rssreader.network;

import java.util.List;

public class Event<Article> {

    private Status status;
    private List<Article> data;

    private Event(Status status) {
        this.status = status;
    }

    private Event(Status status, List<Article> data) {
        this.status = status;
        this.data = data;
    }

    public static Event loading() {
        return new Event(Status.LOADING, null);
    }

    public static <Article> Event success(List<Article> data) {
        return new Event(Status.SUCCESS, data);
    }

    public static <Article> Event error() {
        return new Event(Status.ERROR);
    }

    public Status getStatus() {
        return status;
    }

    public List<Article> getData() {
        return data;
    }

}
