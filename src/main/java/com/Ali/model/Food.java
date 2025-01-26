package com.Ali.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Food")
public class Food {

    @Id
    private ObjectId id;

    private String name;
    private String description;
    private Long price;

    @DBRef(lazy = true)
    private Category foodCategory;

    private List<String> images;
    private boolean available;

    @DBRef(lazy = true)
    private Restaurant restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;

    @DBRef(lazy = true)
    private List<IngredientsItem> ingredients = new ArrayList<>();

    private Date creationDate;
}
