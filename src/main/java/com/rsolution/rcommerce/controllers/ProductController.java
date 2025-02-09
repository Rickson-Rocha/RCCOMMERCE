package com.rsolution.rcommerce.controllers;

import com.rsolution.rcommerce.domain.product.dto.ProductDto;
import com.rsolution.rcommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("v1/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

  @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
      ProductDto product = productService.findById(id);
      return ResponseEntity.ok(product);
  }

  @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(Pageable pageable) {
       return ResponseEntity.ok(productService.findAll(pageable));
  }

  @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        ProductDto newProduct = productService.saveProduct(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(newProduct);
  }

  @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto product) {
        productService.updateProduct(id, product);
        return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
  }



}
