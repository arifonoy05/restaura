package com.restaura.server.restController;

import com.restaura.server.dto.OrderDTO;
import com.restaura.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order", method = RequestMethod.GET)
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<OrderDTO>> getOrders()
    {
        List<OrderDTO> orders= service.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") String id)
    {
        OrderDTO orderDTO = service.getOrderById(id);
        return new ResponseEntity<>(orderDTO, HttpStatus.FOUND);
    }

//    @GetMapping
//    public ResponseEntity<OrderDTO> getOrderByFoodCategoryId(@RequestBody String restaurantId)
//    {
//        List<OrderDTO> orderDTOS = service.getOrderesByFoodCategoryId(foodCategoryId);
//        return new ResponseEntity<>(orderDTOS, HttpStatus.FOUND);
//    }

    @PostMapping("/add")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO)
    {
        OrderDTO result = service.addOrder(orderDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PatchMapping("/update")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO)
    {
        OrderDTO updatedOrder = service.updateOrder(orderDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteOrder(@RequestBody String id)
    {
        if(service.deleteOrder(id))
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
