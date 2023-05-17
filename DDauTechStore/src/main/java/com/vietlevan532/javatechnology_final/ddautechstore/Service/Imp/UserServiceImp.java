package com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.RegistrationDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Cart;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.CartRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.UserRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, CartRepository cartRepository){
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public User checkInfLogin(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean checkUsernameValid(String username) {
        return userRepository.findByUsernameEquals(username)!=null;
    }

    @Transactional
    public void saveUserAndCart(RegistrationDTO registrationDTO) {
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());
        user.setEmail(registrationDTO.getEmail());
        user.setPhone(registrationDTO.getPhone());
        user.setGender(registrationDTO.getGender());

        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);

        cartRepository.save(cart);
    }

    @Override
    public void saveUser(RegistrationDTO registrationDTO) {
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());
        user.setEmail(registrationDTO.getEmail());
        user.setPhone(registrationDTO.getPhone());
        user.setGender(registrationDTO.getGender());
        userRepository.save(user);
    }
}
