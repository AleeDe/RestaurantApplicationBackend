package com.Ali.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.Ali.model.IngredientCategory;
import com.Ali.model.IngredientsItem;

public interface IngredientsService {
    public IngredientCategory createIngredientsCategory(String name, ObjectId restuarantId) throws Exception;

    public IngredientCategory findIngredientsCategoryById(ObjectId Id) throws Exception;

    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(ObjectId restuarantId) throws Exception;

    public IngredientsItem createIngredientsItem(String name, ObjectId categoryId, ObjectId restaurantId) throws Exception;

    public List<IngredientsItem> findRestaIngredients(ObjectId id) throws Exception;

    public IngredientsItem updateStock(ObjectId id) throws Exception;
}
