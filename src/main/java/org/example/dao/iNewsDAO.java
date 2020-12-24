package org.example.dao;

import org.example.model.NewsModel;

import java.util.List;

public interface iNewsDAO extends GenericDAO<NewsModel> {
    List<NewsModel> findByCategoryId(Long categoryId);
    Long save(NewsModel newsModel);
}
