package org.example.service.impl;

import org.example.dao.iCategoryDAO;
import org.example.dao.impl.CategoryDAO;
import org.example.model.CategoryModel;
import org.example.service.iCategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements iCategoryService {

    @Inject
    private iCategoryDAO categoryDAO;

    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }
}
