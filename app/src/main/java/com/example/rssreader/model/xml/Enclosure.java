package com.example.rssreader.model.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.Objects;

@Root(name = "enclosure", strict = false)
public class Enclosure {

    @Attribute(required = false, name = "url")
    private String url;

    public Enclosure() {
    }

    public Enclosure(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enclosure)) return false;
        Enclosure enclosure = (Enclosure) o;
        return Objects.equals(getUrl(), enclosure.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }
}
