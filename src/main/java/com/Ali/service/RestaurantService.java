package com.Ali.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.Ali.dto.RestaurantDto;
import com.Ali.model.Restaurant;
import com.Ali.model.User; // Add this import statement
import com.Ali.request.CreateRestaurantRequest;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    
    public Restaurant updateRestaurant(ObjectId id, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public Restaurant deleteRestaurant(ObjectId id) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurants(String name);

    public Restaurant findRestaurantById(ObjectId id) throws Exception;

    public Restaurant getRestaurantByUserId(ObjectId id) throws Exception;

    public RestaurantDto addFavorites(ObjectId RestaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(ObjectId RestaurantId) throws Exception;
}