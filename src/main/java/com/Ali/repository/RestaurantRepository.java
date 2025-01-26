package com.Ali.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.Ali.model.Restaurant;

public interface RestaurantRepository extends MongoRepository<Restaurant, ObjectId> {

    @Query("{ $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'cuisineType': { $regex: ?0, $options: 'i' } } ] }")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findByOwnerId(ObjectId userId);
}
