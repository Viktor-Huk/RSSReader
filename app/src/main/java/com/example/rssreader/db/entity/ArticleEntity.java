package com.example.rssreader.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "article_table")
public class ArticleEntity {

//    @PrimaryKey(autoGenerate = true)
//    public Long id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "link")
    public String link;

    @ColumnInfo(name = "thumbnailUri")
    public String thumbnailUri;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "pubDate")
    public String pubDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleEntity)) return false;
        ArticleEntity that = (ArticleEntity) o;
        return Objects.equals(getLink(), that.getLink()) &&
                Objects.equals(getThumbnailUri(), that.getThumbnailUri()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getPubDate(), that.getPubDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLink(), getThumbnailUri(), getTitle(), getPubDate());
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

    public ArticleEntity(String link, String thumbnailUri, String title, String pubDate) {
        this.link = link;
        this.thumbnailUri = thumbnailUri;
        this.title = title;
        this.pubDate = pubDate;
    }
}
