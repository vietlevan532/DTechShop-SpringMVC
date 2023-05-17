package com.vietlevan532.javatechnology_final.ddautechstore.Respository;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
