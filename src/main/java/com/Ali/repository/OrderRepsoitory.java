package com.Ali.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ali.model.Order;

public interface OrderRepsoitory extends MongoRepository<Order, ObjectId> {


    public List<Order> findByCustomerId(ObjectId userId);

    public List<Order> findByRestaurantId(ObjectId restaurantId);
    
}