package com.restaura.server.restController;

import com.restaura.server.dto.FoodCategoryDTO;
import com.restaura.server.service.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/food-category", method = RequestMethod.GET)
@RequiredArgsConstructor
public class FoodCategoryRestController {
    private final FoodCategoryService service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<FoodCategoryDTO>> getFoodCategories()
    {
        List<FoodCategoryDTO> foodCategories= service.getFoodCategories();
        return new ResponseEntity<>(foodCategories, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodCategoryDTO> getFoodCategoryById(@PathVariable("id") String id)
    {
        FoodCategoryDTO categoryDTO = service.getCategoryById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.FOUND);
    }

    @GetMapping("/food-category-by-restaurant-id")
    public ResponseEntity<List<FoodCategoryDTO>> getFoodCategoriesByRestaurantId(@RequestBody String restaurantId)
    {
        List<FoodCategoryDTO> foodCategoryDTOS = service.getFoodCategoriesByRestaurantId(restaurantId);
        return new ResponseEntity<>(foodCategoryDTOS, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<FoodCategoryDTO> addFoodCategory(@RequestBody FoodCategoryDTO categoryDTO)
    {
        FoodCategoryDTO result = service.addFoodCategory(categoryDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PatchMapping("/update")
    public ResponseEntity<FoodCategoryDTO> updateFoodCategory(@RequestBody FoodCategoryDTO categoryDTO)
    {
        FoodCategoryDTO updatedCategory = service.updateCategory(categoryDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteFoodCategory(@RequestBody String id)
    {
        if(service.deleteCategory(id))
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
