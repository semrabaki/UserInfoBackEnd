package com.secondhand.springbootsecondhand.dto;

import com.secondhand.springbootsecondhand.model.UserAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAddressConvertor {

    public UserAddressDto convert(UserAddress from){
        return new UserAddressDto(from.getPhoneNumber(), from.getAddress(), from.getCity(), from.getCountry(),
                from.getPostCode());
    }

    public List<UserAddressDto> convert(List<UserAddress> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
