package org.example.mapper;

import org.example.model.NewsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements RowMapper<NewsModel> {
    @Override
    public NewsModel mapRow(ResultSet rs) {
        try{
        NewsModel news = new NewsModel();
        news.setId(rs.getLong("id"));
        news.setTitle(rs.getString("title"));
        return news;
        }catch (SQLException e){
            return null;
        }
    }
}
