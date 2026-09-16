package com.natwest.springdataredisintegration.controller;

import com.natwest.springdataredisintegration.entity.Product;
import com.natwest.springdataredisintegration.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return product;
    }

    @GetMapping("/findAllProducts")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/findProductById/{id}")
    public Product findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public String deleteProductById(@PathVariable int id) {
        return productService.deleteProductById(id);
    }
}
