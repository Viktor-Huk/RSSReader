package com.example.rssreader.utils;

import com.example.rssreader.db.entity.ArticleEntity;
import com.example.rssreader.model.xml.Channel;

import java.util.List;

public interface ListMapper extends Mapper<Channel, List<ArticleEntity>> {
}
