package com.secondhand.springbootsecondhand.dto;

import com.secondhand.springbootsecondhand.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConvertor {

    private final UserAddressConvertor userAddressConvertor;

    public UserDtoConvertor(UserAddressConvertor userAddressConvertor) {
        this.userAddressConvertor = userAddressConvertor;
    }

    public UserDto convert(User from){
        System.out.println("user in UserDto:" + from);
        return new UserDto(
                from.getEmail(),
                from.getFirstName(),
                from.getLastName(),
                from.getUserAddresses()
                .stream()
                .map(userAddressConvertor::convert)
                .collect(Collectors.toList()));
    }

    //for test
    public List<UserDto> convert(List<User> fromList){
        return fromList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
