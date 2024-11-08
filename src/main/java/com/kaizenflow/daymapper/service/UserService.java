package com.kaizenflow.daymapper.service;

import com.kaizenflow.daymapper.entities.User;
import com.kaizenflow.daymapper.exception.UserAlreadyExistAuthenticationException;
import com.kaizenflow.daymapper.mapper.UserMapper;
import com.kaizenflow.daymapper.model.user.UserCreateRequest;
import com.kaizenflow.daymapper.model.user.UserCreateResponse;
import com.kaizenflow.daymapper.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  @Autowired
  public UserService(
      UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  public User getUserByEmail(@NotBlank String email) throws UsernameNotFoundException {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
  }

  public UserCreateResponse createUser(@Valid UserCreateRequest userCreateRequest)
      throws UserAlreadyExistAuthenticationException {
    Objects.requireNonNull(userCreateRequest, "User Create Request cannot be null");
    if (userRepository.existsByUsername(userCreateRequest.userName())) {
      throw new UserAlreadyExistAuthenticationException("Username already exists.");
    }
    if (userRepository.existsByEmail(userCreateRequest.email())) {
      throw new UserAlreadyExistAuthenticationException("Email already exists.");
    }

    User newUser = new User();
    newUser.setUserGuid(UUID.randomUUID());
    newUser.setEmail(userCreateRequest.email());
    newUser.setUsername(userCreateRequest.userName());
    newUser.setFirstName(userCreateRequest.firstName());
    newUser.setLastName(userCreateRequest.lastName());
    newUser.setPasswordHash(passwordEncoder.encode(userCreateRequest.password()));

    User savedUser = userRepository.save(newUser);
    return userMapper.userToUserCreateResponse(savedUser);
  }
}
