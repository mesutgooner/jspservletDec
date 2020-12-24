package org.example.service.impl;

import org.example.dao.iNewsDAO;
import org.example.model.NewsModel;
import org.example.service.iNewsService;

import javax.inject.Inject;
import java.util.List;

public class NewsService implements iNewsService {

    @Inject
    private iNewsDAO newsDAO;

    @Override
    public List<NewsModel> findByCategory(Long categoryId) {
        return newsDAO.findByCategoryId(categoryId);
    }

    @Override
    public NewsModel save(NewsModel newsModel) {
        Long newId = newsDAO.save(newsModel);
        System.out.println(newId);
        return null;
    }
}
