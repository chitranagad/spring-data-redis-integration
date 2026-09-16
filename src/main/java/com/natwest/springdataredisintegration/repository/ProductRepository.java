package com.natwest.springdataredisintegration.repository;

import com.natwest.springdataredisintegration.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ProductRepository {

    public static final String HASH_KEY = "product";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // Using Object template offers maximum compatibility for values

    public Product save(Product product) {
        // Explicitly ensuring the hash field key is stored as a consistent string/object representation
        redisTemplate.opsForHash().put(HASH_KEY, String.valueOf(product.getId()), product);
        log.info("Product saved with ID: {}", product.getId());
        return product;
    }

    public List<Product> findAll() {
        List<Object> values = redisTemplate.opsForHash().values(HASH_KEY);
        log.info("Fetching all products");
        return values.stream()
                .map(obj -> (Product) obj)
                .collect(Collectors.toList());
    }

    public Product findProductById(int id) {
        log.info("Fetching product with ID: {}", id);
        return (Product) redisTemplate.opsForHash().get(HASH_KEY, String.valueOf(id));
    }

    public String deleteProductById(int id) {
        log.info("Deleting product with ID: {}", id);
        redisTemplate.opsForHash().delete(HASH_KEY, String.valueOf(id));
        log.info("Product deleted with ID: {}", id);
        return "Product removed !! " + id;
    }
}
