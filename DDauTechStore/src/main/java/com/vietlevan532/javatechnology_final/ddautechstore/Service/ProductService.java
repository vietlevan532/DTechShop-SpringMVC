package com.vietlevan532.javatechnology_final.ddautechstore.Service;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.ProductDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProduct();

    List<ProductDTO> get8TrendyProduct();

    Product getProductById(Long id);

    Page<ProductDTO> getAllProductPageableByCategoryId(Long categoryId, Pageable pageable);

    Page<ProductDTO> getAllProductPageableByBrandId(Long brandId, Pageable pageable);

    Page<ProductDTO> getAllProductPageable(int pageNumber, int pageSize);

    Page<ProductDTO> getAllSortByPriceAsc(int pageNumber, int pageSize);

    Page<ProductDTO> getAllSortByPriceDes(int pageNumber, int pageSize);

    Page<ProductDTO> getAllSortByName(int pageNumber, int pageSize);
}
