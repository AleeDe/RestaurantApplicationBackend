package com.Ali.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "RestaurantDto")
public class RestaurantDto {
    @Id
    private ObjectId id;

    private String title;
    private List<String> images;
    private String description;
    

}
