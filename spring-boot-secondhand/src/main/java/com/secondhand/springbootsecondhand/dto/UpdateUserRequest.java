package com.secondhand.springbootsecondhand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Component
public class UpdateUserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
}
