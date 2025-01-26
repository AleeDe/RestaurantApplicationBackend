package com.Ali.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ali.model.Food;
import com.Ali.model.Restaurant;
import com.Ali.model.User;
import com.Ali.request.CreateFoodRequest;
import com.Ali.response.MessageResponse;
import com.Ali.service.FoodService;
import com.Ali.service.RestaurantService;
import com.Ali.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        return new ResponseEntity<>(foodService.createFood(req, req.getCategory(), restaurant),HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFood(@PathVariable ObjectId id,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);

        MessageResponse message = new MessageResponse();
        message.setMessage("food deleted successfully");

        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateFoodAvalibilityStatus(@PathVariable ObjectId id,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        userService.findUserByJwtToken(jwt);

        return new ResponseEntity<>(foodService.updateAvalibilityStatus(id),HttpStatus.CREATED);
    }


}
