package com.secondhand.springbootsecondhand.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isActive;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserAddress> userAddresses = new HashSet<>();

    public User() {}

    public User(Long id, String email, String firstName, String lastName, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }
    public User(String email, String firstName, String lastName, Boolean isActive) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }
    //after creating UserDetails
    public User(Long id, String email, String firstName, String lastName,
                 Boolean isActive, Set<UserAddress> userAddresses) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.userAddresses = userAddresses;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Set<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", userAddresses=" + userAddresses +
                '}';
    }

    //?bunlar ne ise yariyor
    //user classindan bir nesne olusturudk bilgileri tutuyoruz iki userin tum bilgilerinin ayni diyelim ama bu userlar ayni degil
    //degerleri ayni olan iki nesneyi esit kanul ediyor-test asamasinda ihtiyac olmus
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName)  && Objects.equals(isActive, user.isActive);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, isActive);
    }
}
