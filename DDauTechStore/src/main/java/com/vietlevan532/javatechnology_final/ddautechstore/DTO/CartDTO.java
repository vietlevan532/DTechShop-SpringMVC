package com.vietlevan532.javatechnology_final.ddautechstore.DTO;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDTO {
    private Long id;

    private User user;

    private List<CartItem> cartItems;
}
