package com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.Order;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.OrderItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.OrderItemRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public Order save(Order order) {
        order = orderRepository.save(order);
        for(OrderItem orderItem: order.getOrderItems()){
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
        return orderRepository.save(order);
    }
}
