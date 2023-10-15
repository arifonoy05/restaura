package com.restaura.server.restController;

import com.restaura.server.dto.DishDTO;
import com.restaura.server.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dish", method = RequestMethod.GET)
@RequiredArgsConstructor
public class DishRestController {

    private final DishService service;


    @GetMapping(value = "/list")
    public ResponseEntity<List<DishDTO>> getFoodCategories()
    {
        List<DishDTO> foodCategories= service.getDishes();
        return new ResponseEntity<>(foodCategories, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable("id") String id)
    {
        DishDTO DishDTO = service.getDishById(id);
        return new ResponseEntity<>(DishDTO, HttpStatus.FOUND);
    }

    @PostMapping("/dishesByFoodCategory")
    public ResponseEntity<List<DishDTO>> addDish(@RequestBody String foodCategoryId)
    {
        List<DishDTO> result = service.getDishesByFoodCategoryId(foodCategoryId);
        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<DishDTO> addDish(@RequestBody DishDTO DishDTO)
    {
        DishDTO result = service.addDish(DishDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PatchMapping("/update")
    public ResponseEntity<DishDTO> updateDish(@RequestBody DishDTO DishDTO)
    {
        DishDTO updatedDish = service.updateDish(DishDTO);
        return new ResponseEntity<>(updatedDish, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteRestaurant(@RequestBody String id)
    {
        if(service.deleteDish(id))
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
