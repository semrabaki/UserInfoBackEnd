package com.secondhand.springbootsecondhand.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class UserAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String phoneNumber;
    private  String address;
    private  String city;
    private  String country;
    private  String postCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserAddress() {}

    public UserAddress(Long id, String phoneNumber, String address,
                       String city, String country, String postCode, User user) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.user = user;
    }

    public UserAddress(String phoneNumber, String address, String city,
                       String country, String postCode, User user) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.user = user;
    }

    public Long getId() {
        return id;
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

    public User getUsers() {
        return user;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
    //Bunlar ne ise yarior
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddress that = (UserAddress) o;
        return Objects.equals(id, that.id) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(postCode, that.postCode) && Objects.equals(user, that.user);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, address, city, country, postCode, user);
    }
}
