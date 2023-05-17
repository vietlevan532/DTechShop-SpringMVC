package com.vietlevan532.javatechnology_final.ddautechstore.DTO;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String gender;

    private List<Order> orders;
}
