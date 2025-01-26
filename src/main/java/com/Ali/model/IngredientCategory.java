package com.Ali.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("IngredientCategory")
public class IngredientCategory {
    @Id
    private ObjectId id;

    private String name;

    @DBRef(lazy = true)
    private Restaurant restaurant;

    @DBRef
    private List<IngredientsItem> ingredients= new ArrayList<>();

}
