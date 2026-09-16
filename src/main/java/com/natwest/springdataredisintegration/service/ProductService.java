package com.natwest.springdataredisintegration.service;

import com.natwest.springdataredisintegration.entity.Product;
import com.natwest.springdataredisintegration.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 1. Automatically updates the cache whenever a product is saved/updated
    @CachePut(value = "product", key = "#product.id")
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // 2. Looks up the global key; skips the database on subsequent hits
    @Cacheable(value = "product", key = "#id", unless = "#result == null")
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    // 3. Properly evicts the unified key ("product::id") out of Redis memory
    @CacheEvict(value = "product", key = "#id")
    public String deleteProductById(int id) {
        return productRepository.deleteProductById(id);
    }
}
