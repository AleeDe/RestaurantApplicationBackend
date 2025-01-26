package com.Ali.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ali.model.Restaurant;
import com.Ali.model.User;
import com.Ali.request.CreateRestaurantRequest;
import com.Ali.response.MessageResponse;
import com.Ali.service.RestaurantService;
import com.Ali.service.UserService;


@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(
        @RequestBody CreateRestaurantRequest req,
        @RequestHeader("Authorization") String jwt) throws Exception {
            
            User user= userService.findUserByJwtToken(jwt);

            Restaurant restaurant=restaurantService.createRestaurant(req, user);

            return new ResponseEntity<>(restaurant,HttpStatus.CREATED);
        }


        @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
        @RequestBody CreateRestaurantRequest req,
        @RequestHeader("Authorization") String jwt,
        @PathVariable ObjectId id) throws Exception {
            
            userService.findUserByJwtToken(jwt);

            Restaurant restaurant=restaurantService.updateRestaurant(id,req);

            return new ResponseEntity<>(restaurant,HttpStatus.ACCEPTED);
        }

        @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(
        @RequestHeader("Authorization") String jwt,
        @PathVariable ObjectId id) throws Exception {
            
            userService.findUserByJwtToken(jwt);

            restaurantService.deleteRestaurant(id);
            MessageResponse response= new MessageResponse();
            response.setMessage("Restaurant deleted successfully");

            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }


        @PutMapping("/{id}/status")
    public ResponseEntity<?> updateRestaurantStatus(
        @RequestHeader("Authorization") String jwt,
        @PathVariable ObjectId id) throws Exception {
            
            userService.findUserByJwtToken(jwt);

            
            return new ResponseEntity<>(restaurantService.updateRestaurantStatus(id),HttpStatus.ACCEPTED);
        }

        @GetMapping("/user")
    public ResponseEntity<?> findRestaurantByUserId(
        @RequestHeader("Authorization") String jwt
        ) throws Exception {
            
            User user=userService.findUserByJwtToken(jwt);

            
            return new ResponseEntity<>(restaurantService.getRestaurantByUserId(user.getId()),HttpStatus.OK);
        }
    
}
