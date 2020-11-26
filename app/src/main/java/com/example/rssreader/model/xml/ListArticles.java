package com.example.rssreader.model.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;
import java.util.Objects;

@Root(name = "rss", strict = false)
public class ListArticles {

    @Element(name = "title")
    @Path("channel")
    private String channelTitle;

    @ElementList(name = "item", inline = true)
    @Path("channel")
    private List<ItemArticle> itemArticleList;

    public ListArticles(){}

    public ListArticles(String channelTitle, List<ItemArticle> itemArticleList) {
        this.channelTitle = channelTitle;
        this.itemArticleList = itemArticleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListArticles)) return false;
        ListArticles listArticles = (ListArticles) o;
        return Objects.equals(getChannelTitle(), listArticles.getChannelTitle()) &&
                Objects.equals(getItemArticleList(), listArticles.getItemArticleList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChannelTitle(), getItemArticleList());
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public List<ItemArticle> getItemArticleList() {
        return itemArticleList;
    }

    public void setItemArticleList(List<ItemArticle> itemArticleList) {
        this.itemArticleList = itemArticleList;
    }
}
