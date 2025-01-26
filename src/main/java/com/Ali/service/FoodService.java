package com.Ali.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.Ali.model.Category;
import com.Ali.model.Food;
import com.Ali.model.Restaurant;
import com.Ali.request.CreateFoodRequest;

public interface FoodService {
    
    public Food createFood(CreateFoodRequest req, Category category,Restaurant restaurant) throws Exception;

    public void deleteFood(ObjectId id) throws Exception;

    public List<Food> getFoods(ObjectId id,
                                boolean isVegitarain,
                                 boolean isNonveg,
                                  boolean isSeasonal,
                                  String foodCategory
    ) throws Exception;

    public List<Food> searchFoods(String keyword) throws Exception;

    public Food findFoodById(ObjectId id) throws Exception;

    public Food updateAvalibilityStatus(ObjectId id) throws Exception;
}
