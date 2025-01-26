package com.Ali.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ali.model.Category;
import com.Ali.model.Restaurant;
import com.Ali.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public Category createCategory(String name, ObjectId id) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(id);

        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);

    }

    @Override
    public List<Category> findCategoryByRestaurantId(ObjectId userId) throws Exception {
        
        Restaurant restaurant= restaurantService.findRestaurantById(userId);

        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(ObjectId categoryId) throws Exception {
        
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isEmpty()){
            throw new Exception("Category not found");
        }else{
        return category.get();
    }
}
    
}
