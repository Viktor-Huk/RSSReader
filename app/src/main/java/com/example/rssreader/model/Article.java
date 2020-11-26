package com.example.rssreader.model;

import java.util.Objects;

public class Article {

    private Long id;
    private String link;
    private String thumbnailUri;
    private String title;
    private String pubDate;

    public Article(Long id, String link, String thumbnailUri, String title, String pubDate) {
        this.id = id;
        this.link = link;
        this.thumbnailUri = thumbnailUri;
        this.title = title;
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", thumbnailUri='" + thumbnailUri + '\'' +
                ", title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(getId(), article.getId()) &&
                Objects.equals(getLink(), article.getLink()) &&
                Objects.equals(getThumbnailUri(), article.getThumbnailUri()) &&
                Objects.equals(getTitle(), article.getTitle()) &&
                Objects.equals(getPubDate(), article.getPubDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLink(), getThumbnailUri(), getTitle(), getPubDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
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
}
