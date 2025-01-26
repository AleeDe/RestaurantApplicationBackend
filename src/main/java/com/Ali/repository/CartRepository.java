package com.Ali.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ali.model.Cart;

public interface CartRepository extends MongoRepository<Cart,ObjectId>{

    public Cart findByCustomerId(ObjectId userId);
    
}
