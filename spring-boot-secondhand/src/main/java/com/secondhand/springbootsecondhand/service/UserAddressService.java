package com.secondhand.springbootsecondhand.service;

import com.secondhand.springbootsecondhand.dto.CreateUserAddressRequest;
import com.secondhand.springbootsecondhand.dto.UpdateUserAddressRequest;
import com.secondhand.springbootsecondhand.dto.UserAddressConvertor;
import com.secondhand.springbootsecondhand.dto.UserAddressDto;
import com.secondhand.springbootsecondhand.exception.UserDetailsNotFoundException;
import com.secondhand.springbootsecondhand.model.User;
import com.secondhand.springbootsecondhand.model.UserAddress;
import com.secondhand.springbootsecondhand.repository.UserAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAddressService {


    private final UserAddressRepository userAddressRepository;
    private final UserService userService;
    private final UserAddressConvertor convertor;

    public UserAddressService(UserAddressRepository userAddressRepository,
                              UserService userService,
                              UserAddressConvertor convertor) {
        this.userAddressRepository = userAddressRepository;
        this.userService = userService;
        this.convertor = convertor;
    }
    public List<UserAddressDto> getAllUserAddress() {
        return userAddressRepository.findAll().stream()
                .map(convertor::convert)
                .collect(Collectors.toList());
    }
    public UserAddressDto createUserAddress(final CreateUserAddressRequest request){

        User user = userService.findUserById(request.getUserId());

        UserAddress userAddress = new UserAddress(request.getPhoneNumber(),
                                                    request.getAddress(),
                                                    request.getCity(),
                                                    request.getCountry(),
                                                    request.getPostCode(),
                                                    user);

        return convertor.convert(userAddressRepository.save(userAddress));
    }
    public UserAddressDto updateUserDetails(final Long userAddressId, final UpdateUserAddressRequest request){
        UserAddress userAddress = findUserDetailsById(userAddressId);

        UserAddress updatedUserAddress = new UserAddress(
                userAddress.getId(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getPostCode(),
                userAddress.getUsers());
        return convertor.convert(userAddressRepository.save(updatedUserAddress));
    }
    public void deleteUserDetails(final Long id){
        findUserDetailsById(id);
        userAddressRepository.deleteById(id);
    }
    private UserAddress findUserDetailsById(final Long userDetailsId){

        return userAddressRepository.findById(userDetailsId)
                .orElseThrow(() -> new UserDetailsNotFoundException("UserDetails could not be found: " + userDetailsId));
    }
}
