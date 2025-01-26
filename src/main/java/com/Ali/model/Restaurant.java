package com.Ali.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Restaurant")
public class Restaurant {
    @Id
    private ObjectId id;

    @DBRef(lazy = true)
    private User owner;

    private String name;
    private String description;
    private String cuisineType;

    @DBRef(lazy = true)
    private Address address;

    private ContactInformation contactInformation;
    private String openingHours;

    @DBRef(lazy = true)
    private List<Order> orders = new ArrayList<>();

    private List<String> images;
    private LocalDateTime registrationDate;
    private boolean open;

    @DBRef(lazy = true)
    private List<Food> foods= new ArrayList<>();
}
