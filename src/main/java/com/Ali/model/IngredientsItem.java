package com.Ali.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "IngredientsItem")
public class IngredientsItem {

    @Id
    private ObjectId id;

    private String name;

    @DBRef
    private IngredientCategory category;

    @DBRef(lazy = true)
    private Restaurant restaurant;

    private boolean inStock=true;
}
