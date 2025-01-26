package com.Ali.service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ali.dto.RestaurantDto;
import com.Ali.model.Address;
import com.Ali.model.Restaurant;
import com.Ali.model.User;
import com.Ali.repository.AddressRepository;
import com.Ali.repository.RestaurantRepository;
import com.Ali.repository.UserRepository;
import com.Ali.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImp implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        
        Address address= addressRepository.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);        
    }

    @Override
    public Restaurant updateRestaurant(ObjectId id, CreateRestaurantRequest updatedRestaurant) throws Exception {
        // Find the existing restaurant by its ID
        Restaurant restaurant = findRestaurantById(id);
        
    
        // Update the restaurant fields with the new values from the updatedRestaurant object
        Address address = addressRepository.save(updatedRestaurant.getAddress());
        restaurant.setAddress(address);
        restaurant.setContactInformation(updatedRestaurant.getContactInformation());
        restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        restaurant.setDescription(updatedRestaurant.getDescription());
        restaurant.setImages(updatedRestaurant.getImages());
        restaurant.setName(updatedRestaurant.getName());
        restaurant.setOpeningHours(updatedRestaurant.getOpeningHours());
    
        // Save the updated restaurant back to the repository
        return restaurantRepository.save(restaurant);
    }
    


    @Override
public Restaurant deleteRestaurant(ObjectId id) throws Exception {
    // Find the existing restaurant by its ID
    Restaurant restaurant = findRestaurantById(id);
    
    // Delete the restaurant from the repository
    restaurantRepository.delete(restaurant);

    // Return the deleted restaurant (optional)
    return restaurant;
}


@Override
public List<Restaurant> getAllRestaurants() {
    // Fetch all restaurants from the repository
    List<Restaurant> restaurants = restaurantRepository.findAll();
    
    // Return the list of restaurants
    return restaurants;
}


@Override
public List<Restaurant> searchRestaurants(String name) {
    // Create a search query
    String searchQuery = name != null ? name.toLowerCase() : "";

    // Use the custom query method in the repository to find matching restaurants
    return restaurantRepository.findBySearchQuery(searchQuery);
}


    @Override
public Restaurant findRestaurantById(ObjectId id) throws Exception {
    // Find the restaurant by its ObjectId
    Restaurant restaurant = restaurantRepository.findById(id)
        .orElseThrow(() -> new Exception("Restaurant with ID " + id + " not found"));

    // Return the found restaurant
    return restaurant;
}


@Override
public Restaurant getRestaurantByUserId(ObjectId userId) throws Exception {
    // Convert the string userId to an ObjectId
    

    // Find the restaurant by ownerId
    Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
    
    // Check if the restaurant is null and throw an exception if not found
    if (restaurant == null) {
        throw new Exception("Restaurant with owner ID " + userId + " not found");
    }

    // Return the found restaurant
    return restaurant;
}


    @Override
    public RestaurantDto addFavorites(ObjectId restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);


        RestaurantDto dto= new RestaurantDto();
        dto.setId(restaurantId);
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());

        if(user.getFavorites().contains(dto)){
            user.getFavorites().remove(dto);
        }
        else{
            user.getFavorites().add(dto);
        }
        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(ObjectId restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant);


    }
    
}
