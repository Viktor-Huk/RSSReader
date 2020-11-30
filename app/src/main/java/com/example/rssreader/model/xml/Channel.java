package com.example.rssreader.model.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;
import java.util.Objects;

@Root(name = "rss", strict = false)
public class Channel {

    @Element(name = "title")
    @Path("channel")
    private String channelTitle;

    @ElementList(name = "item", inline = true)
    @Path("channel")
    private List<ItemArticle> itemArticleList;

    public Channel(){}

    public Channel(String channelTitle, List<ItemArticle> itemArticleList) {
        this.channelTitle = channelTitle;
        this.itemArticleList = itemArticleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        Channel listArticles = (Channel) o;
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

    public List<ItemArticle> getItemArticleList() {
        return itemArticleList;
    }

}
