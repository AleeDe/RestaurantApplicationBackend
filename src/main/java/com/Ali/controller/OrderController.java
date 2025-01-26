package com.Ali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ali.request.AddCartItemRequest;
import com.Ali.request.OrderRequest;
import com.Ali.service.OrderService;
import com.Ali.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {
    

    @Autowired
    private OrderService orderService;


    @Autowired
     private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(orderService.createOrder(req, userService.findUserByJwtToken(jwt)), HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<?> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(orderService.getUserOrder(userService.findUserByJwtToken(jwt).getId()), HttpStatus.OK);
    }
}
