package com.ems.service.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Integer id;

    @NotEmpty
    @Size(min=4,message="username must be min of 4 character !!")
    private String fname;

    @NotEmpty
    @Size(min=4,message="username must be min of 4 character !!")
    private String lname;

    @NotEmpty
    @Email(message = "email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min=3,max=10,message = "Password must be min of 3 chars and max of max 10 chars !!")
    private String password;
}
