package org.example.dao;

import org.example.model.CategoryModel;

import java.util.List;

public interface iCategoryDAO extends GenericDAO<CategoryModel>{
    List<CategoryModel> findAll();
}
