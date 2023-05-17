package com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CartDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Cart;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.CartItemRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.CartRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;
    private  final CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImp(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartDTO addItemToCard(Product product, int quantity, User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);

        }
        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems == null){
            cartItems = new ArrayList<>();
        }
        CartItem cartItem = findCartItem(cartItems, product.getId());
        if (cartItem == null){
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
        } else{
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItemRepository.save(cartItem);
        cart.setCartItems(cartItems);
        cart.setUser(user);
        Cart cart1 = cartRepository.save(cart);
        return mapToCartDTO(cart1);
    }

    @Override
    public CartDTO updateCart(Product product, int quantity, User user) {
        Cart cart = cartRepository.findByUser(user);
        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems == null){
            cartItems = new ArrayList<>();
        }
        CartItem cartItem = findCartItem(cartItems, product.getId());
        if (cartItem == null){
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
        } else{
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItemRepository.save(cartItem);
        cart.setCartItems(cartItems);
        cart.setUser(user);
        Cart cart1 = cartRepository.save(cart);
        return mapToCartDTO(cart1);
    }

    @Override
    public Cart findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public void deleteItemsFromCart(List<Product> products, User user) {
        Cart cart = cartRepository.findByUser(user);
        List<CartItem> cartItems = cart.getCartItems();

        for(Product product: products){
            CartItem cartItem = findCartItem(cartItems, product.getId());
            cartItems.remove(cartItem);
            assert cartItem != null;
            cartItemRepository.delete(cartItem);
        }
        cartRepository.delete(cart);
    }

    public CartDTO mapToCartDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .user(cart.getUser())
                .cartItems(cart.getCartItems())
                .build();
    }

    private CartItem findCartItem(List<CartItem> cartItems, Long productId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(productId)) {
                return cartItem;
            }
        }
        return null;
    }
}
