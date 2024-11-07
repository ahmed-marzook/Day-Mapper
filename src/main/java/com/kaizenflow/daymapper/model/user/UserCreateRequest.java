package com.kaizenflow.daymapper.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
    @NotBlank(message = "User Name cannot be empty")
        @Size(min = 2, max = 30, message = "User Name must be between 2 and 30 characters")
        String userName,
    @Email(message = "Email is not valid") @NotBlank(message = "Email can not be empty")
        String email,
    @NotBlank(message = "Email cannot be empty")
        @Size(min = 1, max = 100, message = "First Name must be between 1 and 100 characters")
        String firstName,
    @NotBlank(message = "Last Name cannot be empty")
        @Size(min = 1, max = 100, message = "Last Name must be between 1 and 30 characters")
        String lastName,
    @NotBlank(message = "Password cannot be empty") String password) {}
