package com.restaura.server.service;

import com.restaura.server.dto.OrderDTO;
import com.restaura.server.entity.Order;
import com.restaura.server.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<OrderDTO> getOrders()
    {
        List<Order> orders = repository.findAll();
        List<OrderDTO> orderDTOs = orders.stream().map(item -> modelMapper.map(item, OrderDTO.class)).collect(Collectors.toList());

        return orderDTOs;
    }

    public OrderDTO getOrderById(String orderId)
    {
        OrderDTO orderDTO = new OrderDTO();
        Optional<Order> order = repository.findById(Long.valueOf(orderId));

        if (order.isPresent())
        {
            orderDTO = modelMapper.map(order, OrderDTO.class);
        }

        return orderDTO;
    }

    public OrderDTO addOrder(OrderDTO orderDTO)
    {
        OrderDTO newOrderDto = new OrderDTO();
        Order order = modelMapper.map(orderDTO, Order.class);

        if(repository.save(order).getId() != 0)
        {
            newOrderDto = modelMapper.map(order, OrderDTO.class);
        }

        return newOrderDto;
    }

    public OrderDTO updateOrder(OrderDTO orderDTO)
    {
        OrderDTO updatedOrderDto = new OrderDTO();
        Optional<Order> order = repository.findById(orderDTO.getId());

        if(order.isPresent())
        {
            Order newOrder = modelMapper.map(orderDTO, Order.class);
            Order updatedOrder = repository.save(newOrder);
            updatedOrderDto = modelMapper.map(updatedOrder, OrderDTO.class);
        }
        return updatedOrderDto;
    }

    public boolean deleteOrder(String id)
    {
        Optional<Order> order = repository.findById(Long.valueOf(id));

        try {
            if(order.isPresent())
            {
                repository.delete(order.get());
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  false;

    }
}
