package com.Ali.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.Ali.model.Order;
import com.Ali.model.User;
import com.Ali.request.OrderRequest;

public interface OrderService {


    public Order  createOrder(OrderRequest order, User user) throws Exception;

    public Order updatOrder(ObjectId orderId, String orderStatus) throws Exception;

    public void cancelOrder(ObjectId orderId) throws Exception;


    public List<Order> getUserOrder(ObjectId userId) throws Exception;

    public List<Order> getRestaurantOrder(ObjectId restaurantId, String orderStatus) throws Exception;

    public Order findOrderById(ObjectId orderId) throws Exception;
    
}
