package com.Ali.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ali.model.Orderitem;

public interface OrderItemRepository extends MongoRepository<Orderitem, ObjectId> {
    
}
