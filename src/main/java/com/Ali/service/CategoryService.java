package com.Ali.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.Ali.model.Category;

public interface CategoryService {

    public Category createCategory(String name, ObjectId userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(ObjectId userId) throws Exception;

    public Category findCategoryById(ObjectId categoryId) throws Exception;
    
}