package com.Ali.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ali.model.User;
import com.Ali.request.AddCartItemRequest;
import com.Ali.request.UpdateCartItemRequest;
import com.Ali.service.CartService;
import com.Ali.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;


    @PutMapping("/cart/add")
    public ResponseEntity<?> addItemToCart(@RequestBody AddCartItemRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(cartService.addItemtoCart(req, jwt), HttpStatus.ACCEPTED);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<?> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity()), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/cart-item/{id}/update")
    public ResponseEntity<?> removeItemFromCart(@PathVariable ObjectId id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(cartService.removeItemFromCart(id, jwt), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<?> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(cartService.clearCart(user.getId()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/cart")
    public ResponseEntity<?> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(cartService.findCartByUser(user.getId()), HttpStatus.OK);
    }

    
    
}
