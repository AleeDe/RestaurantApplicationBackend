package com.Ali.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ali.model.Category;
import com.Ali.model.Food;
import com.Ali.model.Restaurant;
import com.Ali.repository.FoodRepository;
import com.Ali.request.CreateFoodRequest;


@Service
public class FoodServiceImpl implements FoodService{


    @Autowired
    private FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest req,Category foodCategory, Restaurant restaurant) throws Exception {
        Food food = new Food();
        food.setFoodCategory(foodCategory);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasional());
        food.setVegetarian(req.isVegetarin());

        restaurant.getFoods().add(food);

        return foodRepository.save(food);

    }

    @Override
    public void deleteFood(ObjectId id) throws Exception {
        
        Food food = findFoodById(id);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getFoods(ObjectId id,
                                 boolean isVegitarain,
                                  boolean isNonveg,
                                   boolean isSeasonal,
                                    String foodCategory) throws Exception {

        List<Food> foods= foodRepository.findByRestaurantId(id);
        if(isVegitarain){
            foods=filterByVegitarain(foods,isVegitarain);
                    }
        

        if(isNonveg){
            foods=filterByNonveg(foods,isNonveg);
                    }
        
        if(isSeasonal){
            foods=filterBySeasonal(foods,isSeasonal);
                    }
        
        if(foodCategory!=null && !foodCategory.equals("")){
            foods=filterByCategory(foods,foodCategory);
                    }
            
        return foods;
                                                        
    }
                                                
    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food->{
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }else{
                return false;
            }
        }).collect(Collectors.toList());
    }
            
     private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food->food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }
            
    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food->food.isVegetarian()==false).collect(Collectors.toList());
    }
            
    private List<Food> filterByVegitarain(List<Food> foods, boolean isVegitarain) {
        return foods.stream().filter(food->food.isVegetarian()==isVegitarain).collect(Collectors.toList());
    }
            
    @Override
    public List<Food> searchFoods(String keyword) throws Exception {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(ObjectId id) throws Exception {
       return foodRepository.findById(id).orElseThrow(() -> new Exception("Food not found"));
    }

    @Override
    public Food updateAvalibilityStatus(ObjectId id) throws Exception {
        Food food=findFoodById(id);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
    
}
