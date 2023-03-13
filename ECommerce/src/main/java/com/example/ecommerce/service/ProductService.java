package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.VendorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.LoggingMXBean;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private VendorRepository vendorRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.vendorRepository = vendorRepository;
    }

    @Transactional
    public ResponseEntity<?> addProduct(ProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
        product.setVendor(vendorRepository.findById(1L).orElse(null));
        product.setCreatedAt(LocalDate.now());
        Product save = productRepository.save(product);
        ProductDTO map = modelMapper.map(save, ProductDTO.class);
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Product Added successfully");
        result.put("data", map);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getById(Long id){
        Product product = productRepository.findById(id).orElse(null);
        Map<String, Object> result = new HashMap<>();
        if (!Objects.isNull(product)){
            result.put("message", "Product fetch successfully");
            result.put("data", product);
        }
        else
            result.put("message", "Product not Found");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> updateProduct(ProductDTO productDTO, Long id){
        Product product = productRepository.findById(id).orElse(null);
        Map<String, Object> result = new HashMap<>();
        if (!Objects.isNull(product)){
            if (productDTO.getProductImage() != null)
                product.setProductImage(productDTO.getProductImage());
            if (product.getPrice() != null)
                product.setPrice(productDTO.getPrice());
            if (product.getName() != null)
                product.setName(productDTO.getName());
            Product save = productRepository.save(product);
            result.put("message", "Product update successfully");
            result.put("data", save);
        }
        else
            result.put("message", "Product not Found");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteProduct(Long id){
        Product product = productRepository.findById(id).orElse(null);
        Map<String, Object> result = new HashMap<>();
        if (!Objects.isNull(product)){
            productRepository.deleteById(product.getId());
            result.put("message", "Product deleted successfully");
        }
        else
            result.put("message", "Product not Found");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
