package com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.ProductDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Product;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.ProductRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        Collections.shuffle(products);
        return products.stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    /*@Override
    public List<ProductDTO> getAllProductByCategoryId(Long categoryId) {
        List<Product> productsCategories = productRepository.findAll();
        return productsCategories.stream()
                .filter(product -> product.getCategory().getId().equals(categoryId))
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }*/

    @Override
    public Page<ProductDTO> getAllProductPageableByCategoryId(Long categoryId, Pageable pageable) {
        Page<Product> pageResult = productRepository.findAllByCategoryId(categoryId, pageable);
        return pageResult.map(this::mapToProductDTO);
    }

    /*@Override
    public List<ProductDTO> getAllProductByBrandId(Long brandId) {
        List<Product> productsBrands = productRepository.findAll();
        return productsBrands.stream()
                .filter(product -> product.getBrand().getId().equals(brandId))
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }*/

    @Override
    public Page<ProductDTO> getAllProductPageableByBrandId(Long brandId, Pageable pageable) {
        Page<Product> pageResult = productRepository.findAllByBrandId(brandId, pageable);
        return pageResult.map(this::mapToProductDTO);
    }

    @Override
    public Page<ProductDTO> getAllProductPageable(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Product> pageResult = productRepository.findAll(pageable);
        return pageResult.map(this::mapToProductDTO);
    }

    @Override
    public Page<ProductDTO> getAllSortByPriceAsc(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("price").ascending());
        Page<Product> pageResultSortAsc = productRepository.findAll(pageable);
        return pageResultSortAsc.map(this::mapToProductDTO);
    }

    @Override
    public Page<ProductDTO> getAllSortByPriceDes(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("price").descending());
        Page<Product> pageResultSortAsc = productRepository.findAll(pageable);
        return pageResultSortAsc.map(this::mapToProductDTO);
    }

    @Override
    public Page<ProductDTO> getAllSortByName(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("name").ascending());
        Page<Product> pageResultSortAsc = productRepository.findAll(pageable);
        return pageResultSortAsc.map(this::mapToProductDTO);
    }

    public Page<ProductDTO> searchProductsByName(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> products = productRepository.findByNameContaining(keyword, pageable);
        return products.map(this::mapToProductDTO);
    }

    @Override
    public List<ProductDTO> get8TrendyProduct(){
        List<Product> productsTrendy = productRepository.findAll();
        Collections.shuffle(productsTrendy);
        return productsTrendy.stream()
                .limit(8)
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }



    public ProductDTO mapToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .color(product.getColor())
                .description(product.getDescription())
                .category((product.getCategory()))
                .brand((product.getBrand()))
                .orderItems(product.getOrderItems())
                .build();
    }
}
