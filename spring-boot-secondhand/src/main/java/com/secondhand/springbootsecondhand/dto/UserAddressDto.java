package com.secondhand.springbootsecondhand.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserAddressDto {

    private  String phoneNumber;
    private  String address;
    private  String city;
    private  String country;
    private  String postCode;

    public UserAddressDto(String phoneNumber, String address, String city, String country, String postCode) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

}
