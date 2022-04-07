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

    @GetMapping
    public ResponseEntity<List<UserAddressDto>> getAllUserAddress(){
        return ResponseEntity.ok(userAddressService.getAllUserAddress());
    }

    @PostMapping
    public ResponseEntity<UserAddressDto> createUserAddress(@RequestBody CreateUserAddressRequest request){
        return ResponseEntity.ok(userAddressService.createUserAddress(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAddressDto> updateUserDetails(@PathVariable Long id,
                                                            @RequestBody UpdateUserAddressRequest request){
        return ResponseEntity.ok(userAddressService.updateUserDetails(id, request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id){
        userAddressService.deleteUserDetails(id);
        return ResponseEntity.ok().build();
    }








}
