package org.example.dao.impl;

import org.example.dao.iCategoryDAO;
import org.example.mapper.CategoryMapper;
import org.example.model.CategoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements iCategoryDAO {
    @Override
    public List<CategoryModel> findAll() {
        String sql = "select * from category";
        return query(sql,new CategoryMapper());
    }
}
