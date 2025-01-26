package com.Ali.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ali.model.IngredientCategory;
import com.Ali.model.IngredientsItem;
import com.Ali.model.Restaurant;
import com.Ali.repository.IngredientsCategoryRepository;
import com.Ali.repository.IngredientsItemRepository;

@Service
public class IngredientsServiceImpl implements IngredientsService{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;

    @Autowired 
    private IngredientsCategoryRepository ingredientsCategoryRepository;
    @Override
    public IngredientCategory createIngredientsCategory(String name, ObjectId restuarantId) throws Exception {
        Restaurant restaurant= restaurantService.findRestaurantById(restuarantId);

        IngredientCategory category= new IngredientCategory();
        category.setName(name);
        category.setRestaurant(restaurant);

        return ingredientsCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientsCategoryById(ObjectId Id) throws Exception {
        Optional<IngredientCategory> category= ingredientsCategoryRepository.findById(Id);

        if(category.isEmpty()){
            throw new Exception("Category not found");
        }
        return category.get();
    }

    @Override
    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(ObjectId restuarantId) throws Exception {
        restaurantService.findRestaurantById(restuarantId);
        return ingredientsCategoryRepository.findByRestaurantId(restuarantId);
    }

    @Override
    public IngredientsItem createIngredientsItem(String name, ObjectId categoryId, ObjectId restaurantId)
            throws Exception {
        
            Restaurant restaurant= restaurantService.findRestaurantById(restaurantId);
            IngredientCategory category= findIngredientsCategoryById(categoryId);


            IngredientsItem item= new IngredientsItem();
            item.setName(name);
            item.setCategory(category);
            
            IngredientsItem savedItem= ingredientsItemRepository.save(item);

            category.getIngredients().add(savedItem);
            

            return savedItem;
    }

    @Override
    public List<IngredientsItem> findRestaIngredients(ObjectId id) throws Exception {
        
        return ingredientsItemRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem updateStock(ObjectId id) throws Exception {
        
        Optional<IngredientsItem> item= ingredientsItemRepository.findById(id);

        if(item.isEmpty()){
            throw new Exception("Item not found");
        }
        IngredientsItem updatedItem= item.get();
        updatedItem.setInStock(!updatedItem.isInStock());

        
        return ingredientsItemRepository.save(updatedItem);
        
    }
    
}
