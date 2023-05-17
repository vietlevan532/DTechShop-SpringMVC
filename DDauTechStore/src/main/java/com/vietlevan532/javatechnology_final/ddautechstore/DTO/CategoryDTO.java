package com.vietlevan532.javatechnology_final.ddautechstore.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDTO {
    private Long id;
    private String name;

    private List<Product> products;

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
