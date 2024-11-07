package com.kaizenflow.daymapper.controller;

import com.kaizenflow.daymapper.UserAlreadyExistAuthenticationException;
import com.kaizenflow.daymapper.model.user.UserCreateRequest;
import com.kaizenflow.daymapper.model.user.UserCreateResponse;
import com.kaizenflow.daymapper.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserCreateResponse> registerNewUser(
      @RequestBody @Valid UserCreateRequest userCreateRequest)
      throws UserAlreadyExistAuthenticationException {
    return new ResponseEntity<>(userService.createUser(userCreateRequest), HttpStatus.CREATED);
  }
}