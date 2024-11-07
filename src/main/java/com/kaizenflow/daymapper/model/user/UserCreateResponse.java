package com.kaizenflow.daymapper.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;

public record UserCreateResponse(
    Long userId,
    String username,
    String firstName,
    String lastName,
    String email,
    @JsonFormat(pattern = "dd/MM/yyyy") ZonedDateTime createdAt) {}
