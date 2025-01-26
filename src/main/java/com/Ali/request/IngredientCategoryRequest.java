package com.Ali.request;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class IngredientCategoryRequest {
    
    private String name;
    private ObjectId restaurantId;
}
