package com.vietlevan532.javatechnology_final.ddautechstore.Controller;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CategoryDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.DTO.ProductDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.CartItem;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.CategoryService;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CartItemServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.ProductServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShopController {

        @Autowired
        ProductService productService;

        @Autowired
        CategoryService categoryService;

        @Autowired
        CartItemServiceImp cartItemServiceImp;

        @Autowired
        ProductServiceImp productServiceImp;

        @RequestMapping(value = "/shop", method = RequestMethod.GET)
        public String shop(@RequestParam(defaultValue = "1") int page, Model model, HttpServletRequest request) {

            // Filter Categories
            List<CategoryDTO> categories = categoryService.getAllCategory();
            model.addAttribute("categories", categories);

            final int pageSize = 27;
            Page<ProductDTO> pageResult = productService.getAllProductPageable(page, pageSize);
            final int totalPages = pageResult.getTotalPages();
            List<ProductDTO> products = pageResult.getContent();

            model.addAttribute("products", products);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("currentPage", page);

            User user = (User) request.getSession().getAttribute("user");
            List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
            if(cartItems!=null) {
                int totalCartItem = cartItems.size();
                model.addAttribute("totalCartItem", totalCartItem);
            }

            return "shop";
        }

    @RequestMapping(value = "/product_PriceAsc", method = RequestMethod.GET)
    public String shopSortByPriceAsc(@RequestParam(defaultValue = "1") int page, Model model, HttpServletRequest request) {

        // Filter Categories
        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        final int pageSize = 27;
        Page<ProductDTO> pageResult = productService.getAllSortByPriceAsc(page, pageSize);
        final int totalPages = pageResult.getTotalPages();
        List<ProductDTO> products = pageResult.getContent();

        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }

        return "shop";
    }

    @RequestMapping(value = "/product_PriceDes", method = RequestMethod.GET)
    public String shopSortByPriceDes(@RequestParam(defaultValue = "1") int page, Model model, HttpServletRequest request) {

        // Filter Categories
        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        final int pageSize = 27;
        Page<ProductDTO> pageResult = productService.getAllSortByPriceDes(page, pageSize);
        final int totalPages = pageResult.getTotalPages();
        List<ProductDTO> products = pageResult.getContent();

        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }

        return "shop";
    }

    @RequestMapping(value = "/product_SortName", method = RequestMethod.GET)
    public String shopSortByProductTrendy(@RequestParam(defaultValue = "1") int page, Model model, HttpServletRequest request) {

        // Filter Categories
        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        final int pageSize = 27;
        Page<ProductDTO> pageResult = productService.getAllSortByName(page, pageSize);
        final int totalPages = pageResult.getTotalPages();
        List<ProductDTO> products = pageResult.getContent();

        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }

        return "shop";
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchProductsByName(@RequestParam("keyword") String keyword, @RequestParam(defaultValue = "1") int page, Model model, HttpServletRequest request) {

        // Filter Categories
        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        final int pageSize = 27;
        Page<ProductDTO> pageResult = productServiceImp.searchProductsByName(keyword, page, pageSize);
        List<ProductDTO> products = pageResult.getContent();
        int totalPages = pageResult.getTotalPages();

        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = cartItemServiceImp.getAllCartItemByCarID(user);
        if(cartItems!=null) {
            int totalCartItem = cartItems.size();
            model.addAttribute("totalCartItem", totalCartItem);
        }

        return "shop";
    }
}
