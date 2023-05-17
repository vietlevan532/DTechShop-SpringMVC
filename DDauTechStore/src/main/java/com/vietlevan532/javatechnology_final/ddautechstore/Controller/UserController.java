package com.vietlevan532.javatechnology_final.ddautechstore.Controller;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.RegistrationDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request){
        if (userServiceImp.checkInfLogin(username, password) != null) {
            request.getSession().setAttribute("user", userServiceImp.checkInfLogin(username, password));

            request.getSession().getAttribute("user");

            return "redirect:/home";
        }
        return "login";
    }
    @GetMapping("/logout")
    public String goLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerDTO", new RegistrationDTO());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String Register(@ModelAttribute RegistrationDTO registrationDTO, Model model) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getPasswordConfirm())) {
            System.out.println(registrationDTO.getPassword());
            System.out.println(registrationDTO.getPasswordConfirm());
            model.addAttribute("message", "Password not match");
        }
        else if (userServiceImp.checkUsernameValid(registrationDTO.getUsername())) {
            model.addAttribute("message", "Username valid!");
        }else{
            userServiceImp.saveUser(registrationDTO);
            model.addAttribute("message", "Registration success");
        }
        return "register";
    }
}
