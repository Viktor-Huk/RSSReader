package com.example.rssreader.model.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Objects;

@Root(name = "item", strict = false)
public class ItemArticle {

    @Element(name = "link")
    private String link;

    @Element(name = "title")
    private String title;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "enclosure")
    private Enclosure enclosure;

    public ItemArticle() {}

    public ItemArticle(String link, String title, String pubDate, Enclosure enclosure) {
        this.link = link;
        this.title = title;
        this.pubDate = pubDate;
        this.enclosure = enclosure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemArticle)) return false;
        ItemArticle that = (ItemArticle) o;
        return Objects.equals(getLink(), that.getLink()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getPubDate(), that.getPubDate()) &&
                Objects.equals(getEnclosure(), that.getEnclosure());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLink(), getTitle(), getPubDate(), getEnclosure());
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }
}
