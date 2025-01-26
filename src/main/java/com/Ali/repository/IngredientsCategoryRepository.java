package com.Ali.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ali.model.IngredientCategory;

public interface IngredientsCategoryRepository extends MongoRepository<IngredientCategory,ObjectId>{
    
    List<IngredientCategory>  findByRestaurantId(ObjectId id);
}
