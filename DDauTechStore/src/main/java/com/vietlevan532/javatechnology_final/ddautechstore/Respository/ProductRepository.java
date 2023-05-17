package com.vietlevan532.javatechnology_final.ddautechstore.Respository;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
    Page<Product> findAllByBrandId(Long categoryId, Pageable pageable);
    Page<Product> findByNameContaining(String keyword, Pageable pageable);
}
