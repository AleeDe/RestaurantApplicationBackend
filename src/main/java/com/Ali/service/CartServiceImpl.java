package com.Ali.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ali.model.Cart;
import com.Ali.model.CartItem;
import com.Ali.model.Food;
import com.Ali.model.User;
import com.Ali.repository.CardItemRepository;
import com.Ali.repository.CartRepository;
import com.Ali.repository.FoodRepository;
import com.Ali.request.AddCartItemRequest;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CardItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemtoCart(AddCartItemRequest req, String jwt) throws Exception {
        
        User user= userService.findUserByJwtToken(jwt);

        Food food= foodService.findFoodById(req.getFoodId());

        Cart cart= cartRepository.findByCustomerId(user.getId());

        for(CartItem item : cart.getItem()){
            if(item.getFood().equals(food)){
                int newQuantity = item.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(item.getId(), newQuantity);
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setCart(cart);
        cartItem.setIngredients(req.getIngredients());
        cartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.getItem().add(savedCartItem);

        return savedCartItem;

    }

    @Override
    public CartItem updateCartItemQuantity(ObjectId cartItemId, int quantity) throws Exception {

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new Exception("Cart Item not found"));

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice()*quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(ObjectId cartItemId, String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);

        Cart cart= cartRepository.findByCustomerId(user.getId());

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new Exception("Cart Item not found"));
        cart.getItem().remove(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculatorCartTotal(Cart cart) throws Exception {
        Long total = 0L;
        for(CartItem item : cart.getItem()){
            total+=item.getFood().getPrice()*item.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(ObjectId cartId) throws Exception {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->new Exception("Cart not found"));
        cart.setTotal(calculatorCartTotal(cart));
        return cart;
    }

    @Override
    public Cart findCartByUser(ObjectId userId) throws Exception {
        // User user= userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(ObjectId userId) throws Exception {

        // User user= userService.findUserByJwtToken(jwt);

        Cart cart = cartRepository.findByCustomerId(userId);
        cart.getItem().clear();
        return cartRepository.save(cart);
    }
    
}
