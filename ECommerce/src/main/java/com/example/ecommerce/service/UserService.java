package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.entity.Country;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CountryRepository;
import com.example.ecommerce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private CountryRepository countryRepository;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }

    @Transactional
    public ResponseEntity<?> addUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        user.setCreatedAt(LocalDate.now());
        Country country = countryRepository.findByCode(1L);
        user.setCountry(country);
        User savedUser = userRepository.save(user);
        UserDTO map = modelMapper.map(savedUser, UserDTO.class);
        Map<String, Object> result = new HashMap<>();
        result.put("message" , "User created successfully");
        result.put("data", map);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
