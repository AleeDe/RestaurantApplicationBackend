package com.Ali.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Ali.service.OrderService;
import com.Ali.service.UserService;

@RestController
@RequestMapping("/api/admin/")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @GetMapping("/order/resturant/{id}")
    public ResponseEntity<?> getOrderHistory(@PathVariable ObjectId id,@RequestHeader("Authorization") String jwt,@RequestParam(required = false ) String orderStatus) throws Exception {
        return new ResponseEntity<>(orderService.getRestaurantOrder(userService.findUserByJwtToken(jwt).getId(),orderStatus), HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String orderStatus,@PathVariable ObjectId id,@RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(orderService.updatOrder(userService.findUserByJwtToken(jwt).getId(),orderStatus), HttpStatus.OK);
    }
    
}
