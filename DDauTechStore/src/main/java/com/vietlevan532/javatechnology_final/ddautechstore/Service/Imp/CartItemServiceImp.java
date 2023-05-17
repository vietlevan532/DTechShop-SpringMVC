package com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.Cart;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.CartItemRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.CartRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImp implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartItemServiceImp(CartItemRepository cartItemRepository, CartRepository cartRepository){
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public List<CartItem> getAllCartItemByCarID(User user) {
        Cart cart = cartRepository.findByUser(user);
        if(cart!=null) {
            return cart.getCartItems();
        }
        return null;
    }

    public void deleteItemFromCard(User user, Long cartItemId) {
        Cart cart = cartRepository.findByUser(user);
        List<CartItem> cartItemList = cart.getCartItems();
        cartItemList.removeIf(cartItem -> cartItem.getId().equals(cartItemId));
        cartRepository.save(cart);
        cartItemRepository.deleteById(cartItemId);
    }

    public void deleteAllCartItem(User user) {
        Cart cart = cartRepository.findByUser(user);
        cartItemRepository.deleteAllInBatch(cart.getCartItems());
        cartRepository.save(cart);
    }
}
