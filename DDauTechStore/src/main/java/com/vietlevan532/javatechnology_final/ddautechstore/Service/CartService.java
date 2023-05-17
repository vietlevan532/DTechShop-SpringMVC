package com.vietlevan532.javatechnology_final.ddautechstore.Service;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CartDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Cart;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;

import java.util.List;

public interface CartService {
    CartDTO addItemToCard(Product product, int quantity, User user);
    CartDTO updateCart(Product product, int quantity, User user);
    Cart findByUser(User user);

    void deleteItemsFromCart(List<Product> products, User user);

}
