package com.kaizenflow.daymapper.controller;

import com.kaizenflow.daymapper.UserAlreadyExistAuthenticationException;
import com.kaizenflow.daymapper.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserAlreadyExistAuthenticationException.class)
  protected ResponseEntity<ApiError> handleUserAlreadyExist(
      UserAlreadyExistAuthenticationException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(
            HttpStatus.CONFLICT,
            "Username or email is already associated with an account.",
            ex,
            request);
    String field = ex.getMessage().split(" ", 2)[0];
    apiError.addFieldError(null, field, null, ex.getMessage());
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, WebRequest request) {

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", ex, request);

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error ->
                apiError.addFieldError(
                    error.getObjectName(),
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage()));

    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ApiError> handleDefaultExceptions(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "UNCAUGHT ERROR", ex, request);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
