package org.example.service;

import org.example.model.NewsModel;

import java.util.List;

public interface iNewsService {
    List<NewsModel> findByCategory(Long categoryId);
    NewsModel save(NewsModel newsModel);
}
