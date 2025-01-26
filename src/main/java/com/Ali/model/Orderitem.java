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
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Orderitem")
public class Orderitem {

    @Id
    private ObjectId id;

    @DBRef(lazy = true)
    private Food food;

    private int quantity;
    private Long totalPrice;
    private List<String> ingredients;
}
