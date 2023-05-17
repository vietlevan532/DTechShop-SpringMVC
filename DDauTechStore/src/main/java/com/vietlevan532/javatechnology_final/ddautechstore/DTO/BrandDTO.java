package com.vietlevan532.javatechnology_final.ddautechstore.DTO;

import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrandDTO {
    private Long id;
    private String name;
    private String image;

    private List<Product> products;
}
