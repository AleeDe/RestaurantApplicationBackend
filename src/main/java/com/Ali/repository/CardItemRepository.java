package com.Ali.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ali.model.CartItem;

public interface CardItemRepository extends MongoRepository<CartItem,ObjectId>{
    
}
