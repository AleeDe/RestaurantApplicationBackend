package com.Ali.request;

import java.util.List;

import org.bson.types.ObjectId;

import com.Ali.model.Address;
import com.Ali.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
    private ObjectId id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
