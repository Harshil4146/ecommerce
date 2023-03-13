package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        return productService.getById(id);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") Long id){
        return productService.updateProduct(productDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletedProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
