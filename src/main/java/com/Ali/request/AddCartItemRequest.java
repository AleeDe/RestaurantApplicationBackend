package com.Ali.request;

import java.util.List;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class AddCartItemRequest {
    
    private ObjectId foodId;

    private int quantity;

    private List<String> ingredients;
}
