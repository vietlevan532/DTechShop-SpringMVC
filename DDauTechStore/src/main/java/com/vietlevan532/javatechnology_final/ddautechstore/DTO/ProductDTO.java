package com.vietlevan532.javatechnology_final.ddautechstore.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Brand;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Category;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.OrderItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String image;
    private Integer price;
    private String color;
    private String description;

    private Category category;

    private Brand brand;

    private List<OrderItem> orderItems;
}
