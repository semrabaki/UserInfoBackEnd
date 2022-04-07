package com.secondhand.springbootsecondhand.dto;


import java.util.List;

public class UserDto {

    private String email;
    private String firstName;
    private String lastName;

    private List<UserAddressDto> userAddressDtoList;

    public UserDto() {}

    public UserDto(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto(String email, String firstName, String lastName, List<UserAddressDto> userAddressDtoList) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAddressDtoList = userAddressDtoList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserAddressDto> getUserAddressDtoList() {
        return userAddressDtoList;
    }

    public void setUserAddressDtoList(List<UserAddressDto> userAddressDtoList) {
        this.userAddressDtoList = userAddressDtoList;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userAddressDtoList=" + userAddressDtoList +
                '}';
    }
}
