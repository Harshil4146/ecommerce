package com.example.ecommerce.service;

import com.example.ecommerce.entity.Vendor;
import com.example.ecommerce.repository.CountryRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> addVendor(Vendor vendor){
        vendor.setCountry(countryRepository.findByCode(1L));
        vendor.setUser(userRepository.findById(2L).orElse(null));
        vendor.setCreatedAt(LocalDate.now());
        Vendor save = vendorRepository.save(vendor);
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Vendor added successfully");
        result.put("data", save);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
