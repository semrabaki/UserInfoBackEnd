package com.secondhand.springbootsecondhand.controller;

import com.secondhand.springbootsecondhand.dto.CreateUserAddressRequest;
import com.secondhand.springbootsecondhand.dto.UpdateUserAddressRequest;
import com.secondhand.springbootsecondhand.dto.UserAddressDto;
import com.secondhand.springbootsecondhand.service.UserAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user-address")
public class UserAddressController {

    private final UserAddressService userAddressService;

    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id){
        userAddressService.deleteUserDetails(id);
        return ResponseEntity.ok().build();
    }








}
