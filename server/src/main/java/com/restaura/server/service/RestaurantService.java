package com.restaura.server.service;

import com.restaura.server.dto.RestaurantDTO;
import com.restaura.server.entity.Restaurant;
import com.restaura.server.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        // Setting creation time
        restaurant.setCreatedAt(timestamp);

        if(restaurantRepository.save(restaurant).getId() != 0 ){
            restaurantDTO = modelMapper.map(restaurant, RestaurantDTO.class);
            return restaurantDTO;
        }
        else return new RestaurantDTO();
    }

    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantDTO> resultList = restaurantList.stream().map( item -> modelMapper.map(item, RestaurantDTO.class)).collect(Collectors.toList());
        System.out.println(resultList);
        return resultList;
    }

    public RestaurantDTO getRestaurantById(String id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(Long.valueOf(id));
        RestaurantDTO result = new RestaurantDTO();
        if(restaurant.isPresent()){
            result = modelMapper.map(restaurant.get(), RestaurantDTO.class);
        }
        return result;
    }

    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        RestaurantDTO result = new RestaurantDTO();

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantDTO.getId());
        if(restaurant.isPresent()){
            Restaurant updatedData = modelMapper.map(restaurantDTO, Restaurant.class);
            updatedData.setUpdatedAt(timestamp);

            Restaurant updatedRestaurant = restaurantRepository.save(updatedData);
            System.out.println("Updated restaurant\n" + updatedRestaurant);
            result = modelMapper.map(updatedRestaurant, RestaurantDTO.class);
        }
        return result;
    }

    public Boolean deleteRestaurant(String id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(Long.valueOf(id));
        try{
            if(restaurant.isPresent()){
                restaurantRepository.delete(restaurant.get());
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
