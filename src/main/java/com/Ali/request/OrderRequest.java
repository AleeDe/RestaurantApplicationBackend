package com.Ali.request;

import org.bson.types.ObjectId;

import com.Ali.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
    
    private  ObjectId restaurantId;

    private Address deliveryAddress;

}
