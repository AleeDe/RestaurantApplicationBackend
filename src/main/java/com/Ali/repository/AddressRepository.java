package com.Ali.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ali.model.Address;

public interface AddressRepository extends MongoRepository<Address,ObjectId>{
    
}
