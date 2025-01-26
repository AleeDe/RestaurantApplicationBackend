package com.Ali.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.Ali.model.IngredientCategory;
import com.Ali.model.IngredientsItem;
import com.Ali.request.IngredientCategoryRequest;
import com.Ali.request.IngredientRequest;
import com.Ali.service.IngredientsService;
import com.Ali.service.UserService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientService;

    @Autowired
    private UserService userService;

    @PostMapping("/category")
    public ResponseEntity<?> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception{
        
        userService.findUserByJwtToken(jwt);

        IngredientCategory item=ingredientService.createIngredientsCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item,HttpStatus.CREATED);
    }


    @PostMapping
    public ResponseEntity<?> createIngredientItem(
            @RequestBody IngredientRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception{
        
        userService.findUserByJwtToken(jwt);

        IngredientsItem item=ingredientService.createIngredientsItem(req.getName(), req.getCategoryId(), req.getRestaurantId());
        return new ResponseEntity<>(item,HttpStatus.CREATED);
    }


    @PutMapping("/{id}/stoke")
    public ResponseEntity<?> updateIngredientStock(
            @PathVariable ObjectId id,
            @RequestHeader("Authorization") String jwt) throws Exception{
        
        userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(ingredientService.updateStock(id),HttpStatus.ACCEPTED);
    }



    // check this
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<?> getRestaurantIngredient(
            @PathVariable ObjectId id,
            @RequestHeader("Authorization") String jwt) throws Exception{
        
        userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(ingredientService.findIngredientsCategoryByRestaurantId(id),HttpStatus.ACCEPTED);
    }

    
    // check this

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<?> getRestaurantIngredientCategory(
            @PathVariable ObjectId id,
            @RequestHeader("Authorization") String jwt) throws Exception{
        
        userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(ingredientService.findIngredientsCategoryById(id),HttpStatus.ACCEPTED);
    }
    
}
