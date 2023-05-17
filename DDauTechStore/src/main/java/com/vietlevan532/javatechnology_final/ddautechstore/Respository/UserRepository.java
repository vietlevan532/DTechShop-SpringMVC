package com.vietlevan532.javatechnology_final.ddautechstore.Respository;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsernameEquals(String username);
}
