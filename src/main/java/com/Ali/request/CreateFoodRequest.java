package com.Ali.request;

import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.Ali.model.Category;
import com.Ali.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
    
    @Id
    private ObjectId id;
    
    private String name;
    private String description;
    private Long price;
    

    private Category category;
    private List<String> images;

    private ObjectId restaurantId;

    private boolean vegetarin;
    private boolean seasional;
    private List<IngredientsItem> ingredients;
}
