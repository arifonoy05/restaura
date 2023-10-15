package com.restaura.server.service;

import com.restaura.server.dto.FoodCategoryDTO;
import com.restaura.server.entity.FoodCategory;
import com.restaura.server.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodCategoryService {

    private final FoodCategoryRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();


    public List<FoodCategoryDTO> getFoodCategories()
    {
        List<FoodCategory> categories = repository.findAll();
        List<FoodCategoryDTO> resultList = categories.stream().map( item -> modelMapper.map(item, FoodCategoryDTO.class)).collect(Collectors.toList());
        System.out.println(resultList);
        return resultList;
    }

    public List<FoodCategoryDTO> getFoodCategoriesByRestaurantId(String restaurantId)
    {

        List<FoodCategory> foodCategories = repository.findAllByRestaurantId(Long.valueOf(restaurantId));

        List<FoodCategoryDTO> foodCategoryDTOs = foodCategories.stream().map(item -> modelMapper.map(item, FoodCategoryDTO.class)).collect(Collectors.toList());

        return foodCategoryDTOs;
    }

    public FoodCategoryDTO getCategoryById(String categoryId)
    {
        Optional<FoodCategory> foodCategory = repository.findById(Long.valueOf(categoryId));
        FoodCategoryDTO categoryDTO = new FoodCategoryDTO();
        if(foodCategory.isPresent())
        {
            categoryDTO = modelMapper.map(foodCategory.get(), FoodCategoryDTO.class);
        }

        return categoryDTO;
    }

    public FoodCategoryDTO addFoodCategory(FoodCategoryDTO categoryDTO)
    {
        FoodCategoryDTO newFoodCategoryDto = new FoodCategoryDTO();

        FoodCategory category = modelMapper.map(categoryDTO, FoodCategory.class);
        if(repository.save(category).getId() != 0)
        {
            newFoodCategoryDto = modelMapper.map(category, FoodCategoryDTO.class);

        }

        return newFoodCategoryDto;
    }

    public FoodCategoryDTO updateCategory(FoodCategoryDTO categoryDTO)
    {
        FoodCategoryDTO updatedCategoryDto = new FoodCategoryDTO();

        Optional<FoodCategory> previousCategory = repository.findById(categoryDTO.getId());
        if (previousCategory.isPresent())
        {
            FoodCategory updatedData = modelMapper.map(updatedCategoryDto, FoodCategory.class);
            FoodCategory updatedCategory = repository.save(updatedData);
            updatedCategoryDto = modelMapper.map(updatedCategory, FoodCategoryDTO.class);
        }

        return updatedCategoryDto;
    }

    public boolean deleteCategory(String id)
    {
        Optional<FoodCategory> category = repository.findById(Long.valueOf(id));
        try {
            if (category.isPresent())
            {
                repository.delete(category.get());
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
