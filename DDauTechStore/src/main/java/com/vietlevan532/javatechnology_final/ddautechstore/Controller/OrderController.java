package com.vietlevan532.javatechnology_final.ddautechstore.Controller;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CartItemServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.CartServiceImp;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.OrderItemServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @Autowired
    OrderItemServiceImp orderItemServiceImp;

    @Autowired
    CartServiceImp cartServiceImp;

    @Autowired
    CartItemServiceImp cartItemServiceImp;

    @GetMapping("/order")
    public String Order(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("user");
        cartItemServiceImp.deleteAllCartItem(user);
        return "checkout";
    }
}
