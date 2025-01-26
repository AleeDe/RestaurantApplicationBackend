package com.Ali.request;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class IngredientRequest {
    private String name;
    private ObjectId categoryId;
    private ObjectId restaurantId;
}
