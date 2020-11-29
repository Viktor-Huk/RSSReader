package com.example.rssreader.utils.mapper;

import com.example.rssreader.db.entity.ArticleEntity;
import com.example.rssreader.model.xml.Channel;

import java.util.List;

public interface MapperChannelToList extends Mapper<Channel, List<ArticleEntity>> {
}
