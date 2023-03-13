package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Vendor;
import com.example.ecommerce.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@RequestBody Vendor vendor){
        return vendorService.addVendor(vendor);
    }
}
