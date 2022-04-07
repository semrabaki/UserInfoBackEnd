package com.secondhand.springbootsecondhand.controller;

import com.secondhand.springbootsecondhand.dto.CreateUserRequest;
import com.secondhand.springbootsecondhand.dto.UpdateUserRequest;
import com.secondhand.springbootsecondhand.dto.UserDto;
import com.secondhand.springbootsecondhand.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{mail}")
    public ResponseEntity<UserDto> getUserByMail(@PathVariable("mail") String mail){
        return ResponseEntity.ok(userService.getUserByMail(mail));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody CreateUserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping("/{mail}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("mail") String mail,
                                              @RequestBody UpdateUserRequest updateUserRequest){
        return ResponseEntity.ok(userService.updateUser(mail, updateUserRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id){
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<Void> activeUser(@PathVariable("id") Long id){
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
