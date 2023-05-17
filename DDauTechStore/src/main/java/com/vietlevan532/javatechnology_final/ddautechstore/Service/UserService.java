package com.vietlevan532.javatechnology_final.ddautechstore.Service;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.RegistrationDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;

public interface UserService {
    void saveUser(RegistrationDTO registrationDTO);
    User checkInfLogin(String username, String password);
    boolean checkUsernameValid(String username);
}
