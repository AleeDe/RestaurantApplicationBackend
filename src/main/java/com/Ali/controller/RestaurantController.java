package com.Ali.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.Ali.model.Restaurant;
import com.Ali.model.User;

import com.Ali.service.RestaurantService;
import com.Ali.service.UserService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<?> searchRestaurant(
        @RequestHeader("Authorization") String jwt,
        @RequestParam String keyword
        ) throws Exception {
            
            userService.findUserByJwtToken(jwt);

            List<Restaurant> restaurant=restaurantService.searchRestaurants(keyword);

            return new ResponseEntity<>(restaurant,HttpStatus.OK);
        }


        @GetMapping()
    public ResponseEntity<?> getAllRestaurant(
        @RequestHeader("Authorization") String jwt
        ) throws Exception {
            
            userService.findUserByJwtToken(jwt);
            return new ResponseEntity<>(restaurantService.getAllRestaurants(),HttpStatus.OK);
        }


        @GetMapping("/{id}")
        public ResponseEntity<?> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable ObjectId id
            ) throws Exception {
                
                userService.findUserByJwtToken(jwt);
                return new ResponseEntity<>(restaurantService.findRestaurantById(id),HttpStatus.OK);
            }


            @PutMapping("/{id}/add-favorites")
        public ResponseEntity<?> addFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable ObjectId id
            ) throws Exception {
                
                User user=userService.findUserByJwtToken(jwt);
                return new ResponseEntity<>(restaurantService.addFavorites(id, user),HttpStatus.ACCEPTED);
            }

}
