package com.Ali.request;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class UpdateCartItemRequest {

    @Id
    private ObjectId cartItemId;

    private int quantity;
    
}
