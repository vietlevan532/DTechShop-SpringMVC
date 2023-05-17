package com.vietlevan532.javatechnology_final.ddautechstore.Controller;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CartDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CategoryDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Cart;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CartItemServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CartServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CategoryServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.ProductServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    ProductServiceImp productService;
    @Autowired
    CategoryServiceImp categoryService;
    @Autowired
    CartServiceImp cartService;

    @Autowired
    CartItemServiceImp cartItemServiceImp;

    @GetMapping("/cart")
    public String cart(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("user");
        Cart cart = cartService.findByUser(user);
        model.addAttribute("cartUser", cart);

        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        int totalCartItem = cartItems.size();
        model.addAttribute("totalCartItem", totalCartItem);

        int subTotalPrice = 0;
        for (CartItem cartItem:
                cartItems) {
            subTotalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        model.addAttribute("subtotal", subTotalPrice);
        model.addAttribute("total", (subTotalPrice+10));

        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        return "cart";
    }

    @RequestMapping(value = "/cart/add/{id}", method = RequestMethod.GET)
    public String addToCart(@PathVariable("id")Long id, HttpServletRequest request, Model model) {
        Product product = productService.getProductById(id);
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            CartDTO cartDTO = cartService.addItemToCard(product, 1, user);
            List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
            if(cartItems!=null) {
                int totalCartItem = cartItems.size();
                model.addAttribute("totalCartItem", totalCartItem);
            }

            return "redirect:/cart";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/cartItem/{cartItemId}/delete", method = RequestMethod.GET)
    public String deleteCartItem(@PathVariable("cartItemId") Long cartItemId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        cartItemServiceImp.deleteItemFromCard(user, cartItemId);
        return "redirect:/cart";
    }
}
