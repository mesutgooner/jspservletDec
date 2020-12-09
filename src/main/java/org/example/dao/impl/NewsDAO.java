package org.example.dao.impl;

import org.example.dao.iNewsDAO;
import org.example.mapper.NewsMapper;
import org.example.model.NewsModel;

import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements iNewsDAO {
    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        String sql = "select * from news where categoryid = ?";
        return query(sql,new NewsMapper(), categoryId);
    }
}
