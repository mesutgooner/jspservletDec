package org.example.dao.impl;

import org.example.dao.iNewsDAO;
import org.example.mapper.NewsMapper;
import org.example.model.NewsModel;

import java.sql.*;
import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements iNewsDAO {
    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        String sql = "select * from news where categoryid = ?";
        return query(sql, new NewsMapper(), categoryId);
    }

    @Override
    public Long save(NewsModel newsModel) {
            String sql = "insert into news (title,content,categoryid) values(?,?,?)";
            return insert(sql, newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryID());
    }
}
