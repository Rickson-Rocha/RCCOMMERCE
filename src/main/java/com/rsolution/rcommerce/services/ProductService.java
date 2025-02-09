package com.rsolution.rcommerce.services;

import com.rsolution.rcommerce.domain.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
     ProductDto findById(long id);
     Page<ProductDto> findAll(Pageable pageable);
     ProductDto saveProduct(ProductDto productDto);
     void updateProduct(Long id, ProductDto productDto);
     void deleteProduct(Long id);
    
}
