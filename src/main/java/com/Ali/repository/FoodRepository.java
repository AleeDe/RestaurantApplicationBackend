package com.Ali.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Ali.model.Food;
import java.util.List;

public interface FoodRepository extends MongoRepository<Food, ObjectId> {

    List<Food> findByRestaurantId(ObjectId restaurantId);

    @Query("{ '$or': [ { 'name': { $regex: ?0, $options: 'i' } }, { 'foodCategory.name': { $regex: ?0, $options: 'i' } } ] }")
    List<Food> searchFood(@Param("keyword") String keyword);
}
