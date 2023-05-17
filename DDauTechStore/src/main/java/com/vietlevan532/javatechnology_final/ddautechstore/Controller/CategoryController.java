package com.vietlevan532.javatechnology_final.ddautechstore.Controller;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CategoryDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.DTO.ProductDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.CategoryService;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CartItemServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class CategoryController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CartItemServiceImp cartItemServiceImp;

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String shopByCategory(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request) {

        final int pageSize = 27;
        Page<ProductDTO> pageResult = productService.getAllProductPageableByCategoryId(id, PageRequest.of(page, pageSize));
        List<ProductDTO> products = pageResult.getContent();
        final int totalPages = pageResult.getTotalPages();
        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }


        return "shop";
    }
}