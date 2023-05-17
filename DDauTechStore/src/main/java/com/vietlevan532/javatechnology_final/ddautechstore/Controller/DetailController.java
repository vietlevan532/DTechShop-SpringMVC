package com.vietlevan532.javatechnology_final.ddautechstore.Controller;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CategoryDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.DTO.ProductDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.CategoryService;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CartItemServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DetailController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartItemServiceImp cartItemServiceImp;

    //DETAIL VIEW
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String productDetail(@PathVariable("id")Long id, Model model, HttpServletRequest request){

        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        List<ProductDTO> productsTrendy = productService.get8TrendyProduct();
        model.addAttribute("productsTrendy", productsTrendy);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }

        return "detail";
    }
}
