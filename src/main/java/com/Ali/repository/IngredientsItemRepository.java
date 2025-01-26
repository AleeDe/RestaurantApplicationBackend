package com.Ali.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ali.model.IngredientsItem;

public interface IngredientsItemRepository extends MongoRepository<IngredientsItem, ObjectId> {
    List<IngredientsItem> findByRestaurantId(ObjectId id);
}
