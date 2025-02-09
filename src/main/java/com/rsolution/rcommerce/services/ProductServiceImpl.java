package com.rsolution.rcommerce.services;

import com.rsolution.rcommerce.domain.product.Product;
import com.rsolution.rcommerce.domain.product.dto.ProductDto;
import com.rsolution.rcommerce.repositories.ProductRepository;
import com.rsolution.rcommerce.services.exceptions.DatabaseException;
import com.rsolution.rcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServiceImpl  implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found; " + id));
        return new ProductDto( product);
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> product = productRepository.findAll(pageable);
        return product.map(ProductDto::new);

    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        copyToEntity(productDto, product);
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    @Transactional
    public void updateProduct(Long id, ProductDto productDto) {
        try{
            Product product = productRepository.getReferenceById(id);
            copyToEntity(productDto,product);
            productRepository.save(product);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found; " + id);
        }

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found; " + id);
        }
        try{
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Referential integrity failure");
        }

    }


    private void copyToEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());


    }
}
