package com.Ali.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ali.model.Food;
import com.Ali.model.Restaurant;
import com.Ali.model.User;
import com.Ali.request.CreateFoodRequest;
import com.Ali.service.FoodService;
import com.Ali.service.RestaurantService;
import com.Ali.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {


    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<?> searchFood(@RequestParam String name,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        userService.findUserByJwtToken(jwt);

        return new ResponseEntity<>(foodService.searchFoods(name),HttpStatus.OK);
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<?> getRestaurantFood(@PathVariable ObjectId id,
                                                @RequestParam boolean vegetarian,
                                                @RequestParam boolean seasional,
                                                @RequestParam boolean nonveg,
                                                @RequestParam(required = false) String foodCategory,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        userService.findUserByJwtToken(jwt);

        return new ResponseEntity<>(foodService.getFoods(id, vegetarian, nonveg, seasional, foodCategory),HttpStatus.OK);
    }
    
}
