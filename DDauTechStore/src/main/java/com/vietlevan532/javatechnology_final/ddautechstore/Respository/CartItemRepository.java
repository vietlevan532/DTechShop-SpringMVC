package com.vietlevan532.javatechnology_final.ddautechstore.Respository;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
