package com.Ali.service;

import org.bson.types.ObjectId;

import com.Ali.model.Cart;
import com.Ali.model.CartItem;
import com.Ali.request.AddCartItemRequest;

public interface CartService {
    
    public CartItem addItemtoCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(ObjectId cartItemId,int quantity) throws Exception;

    public Cart removeItemFromCart(ObjectId cartItemId, String jwt) throws Exception;

    public Long calculatorCartTotal(Cart cart) throws Exception;

    public Cart findCartById(ObjectId cartId) throws Exception;

    public Cart findCartByUser(ObjectId userId) throws Exception;

    public Cart clearCart(ObjectId userId) throws Exception;
}
