package com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.BrandDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Brand;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.BrandRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImp(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDTO> getAllBrand() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(this::mapToBrandDTO)
                .collect(Collectors.toList());
    }

    /*@Override
    public List<BrandDTO> getAllBrandByID(Long id) {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .filter(brand -> brand.getId().equals(id))
                .map(this::mapToBrandDTO)
                .collect(Collectors.toList());
    }*/

    public BrandDTO mapToBrandDTO(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .image(brand.getImage())
                .products(brand.getProducts())
                .build();
    }
}
