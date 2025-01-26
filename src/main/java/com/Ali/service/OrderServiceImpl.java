package com.Ali.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ali.model.Address;
import com.Ali.model.Cart;
import com.Ali.model.CartItem;
import com.Ali.model.Order;
import com.Ali.model.Orderitem;
import com.Ali.model.Restaurant;
import com.Ali.model.User;
import com.Ali.repository.AddressRepository;
import com.Ali.repository.OrderItemRepository;
import com.Ali.repository.OrderRepsoitory;
import com.Ali.repository.RestaurantRepository;
import com.Ali.repository.UserRepository;
import com.Ali.request.OrderRequest;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepsoitory orderRepsoitory;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        

        Address shippingAddress = order.getDeliveryAddress();
        addressRepository.save(shippingAddress);

        if(!user.getAddress().contains(shippingAddress)){
            user.getAddress().add(shippingAddress);
            userRepository.save(user);
        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setRestaurant(restaurant);
        createdOrder.setDeliveryAddress(shippingAddress);


        Cart cart = cartService.findCartById(user.getId());

        List<Orderitem> orderItems = new ArrayList<>();

        for(CartItem item : cart.getItem()){
            Orderitem orderItem = new Orderitem();
            orderItem.setFood(item.getFood());
            orderItem.setIngredients(item.getIngredients());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getTotalPrice());
            
            Orderitem savedOrderItem=orderItemRepository.save(orderItem);
            
            orderItems.add(orderItem);
        }
        
        Long totalPrice=cartService.calculatorCartTotal(cart);

        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepsoitory.save(createdOrder);
        
        restaurant.getOrders().add(savedOrder);

        return savedOrder;





    }

    @Override
    public Order updatOrder(ObjectId orderId, String orderStatus) throws Exception {
        Order order= findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY") 
        || orderStatus.equals("DELIVERED") 
        || orderStatus.equals("PENDING") 
        || orderStatus.equals("COMPLETED")
         ){
            order.setOrderStatus(orderStatus);
            return orderRepsoitory.save(order);
            
        }else{
            throw new Exception("Please Selete a valid Order Status");
        }
    }

    @Override
    public void cancelOrder(ObjectId orderId) throws Exception {
        
        Order order= findOrderById(orderId);
        orderRepsoitory.deleteById(orderId);
        // order.setOrderStatus("CANCELLED");
    }

    @Override
    public List<Order> getUserOrder(ObjectId userId) throws Exception {
        return orderRepsoitory.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrder(ObjectId restaurantId, String orderStatus) throws Exception {
        List<Order> order=  orderRepsoitory.findByRestaurantId(restaurantId);

            if(orderStatus!=null){
                order= order.stream().filter(o->o.getOrderStatus().equals(orderStatus)).toList();
            }

            return order;
        
    }

    @Override
    public Order findOrderById(ObjectId orderId) throws Exception {
        Optional<Order> optionalOrder =orderRepsoitory.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order Not Found");
        }
        return optionalOrder.get();
    }
    
}
