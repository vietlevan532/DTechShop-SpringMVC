package com.vietlevan532.javatechnology_final.ddautechstore.Respository;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
