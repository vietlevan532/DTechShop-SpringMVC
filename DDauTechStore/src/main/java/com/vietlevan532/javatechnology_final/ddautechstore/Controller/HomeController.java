package com.vietlevan532.javatechnology_final.ddautechstore.Controller;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.BrandDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CategoryDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.DTO.ProductDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.BrandService;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.CategoryService;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CartItemServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(HttpServletRequest request){
        request.getSession().setAttribute("username","user1");
        return "redirect:/home";
    }


    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
    @Autowired
    CartItemServiceImp cartItemServiceImp;
    // INDEX VIEW
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String Index(Model model, HttpServletRequest request) {

        List<ProductDTO> productsTrendy = productService.get8TrendyProduct();
        model.addAttribute("productsTrendy", productsTrendy);

        List<ProductDTO> products = productService.getAllProduct();
        model.addAttribute("products", products);

        List<BrandDTO> brands = brandService.getAllBrand();
        model.addAttribute("brands", brands);

        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }

        return "index";
    }

    //CHECK OUT VIEW
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String Detail(Model model, HttpServletRequest request) {
        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }
        return "checkout";
    }

    //CONTACT VIEW
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String Contact(Model model, HttpServletRequest request) {
        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }
        return "contact";
    }

}
