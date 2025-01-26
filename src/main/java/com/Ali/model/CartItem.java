package com.Ali.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("CartItem")
public class CartItem {

    @Id
    private ObjectId id;

    @DBRef(lazy = true)
    private Cart cart;

    @DBRef
    private Food food;

    private int quantity;
    private List<String> ingredients;
    private Long totalPrice;
}
