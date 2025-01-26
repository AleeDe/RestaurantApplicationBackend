package com.Ali.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ali.model.Category;

public interface CategoryRepository extends MongoRepository<Category,ObjectId>{

    public List<Category> findByRestaurantId(ObjectId id);
    
}
