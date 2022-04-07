package com.secondhand.springbootsecondhand.dto;
import org.springframework.stereotype.Component;

@Component
public class CreateUserRequest {

    private String email;
    private String firstName;
    private String lastName;

    public CreateUserRequest() {}

    public CreateUserRequest(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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

}
