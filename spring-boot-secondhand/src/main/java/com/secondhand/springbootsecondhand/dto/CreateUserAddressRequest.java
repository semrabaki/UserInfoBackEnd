package com.secondhand.springbootsecondhand.dto;

public class CreateUserAddressRequest {

    private  String phoneNumber;
    private  String address;
    private  String city;
    private  String country;
    private  String postCode;

    private Long userId;

    public CreateUserAddressRequest() {}

    public CreateUserAddressRequest(String phoneNumber, String address,
                                    String city, String country, String postCode, Long userId) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.userId = userId;
    }
    public CreateUserAddressRequest(String phoneNumber, String address,
                                    String city, String country, String postCode) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }

    public Long getUserId() {
        return userId;
    }
}
