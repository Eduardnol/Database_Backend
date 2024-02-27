package com.example.database_user.services.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UsersNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ApiError> handleUsersNotFoundException() {
    ApiError apiError = new ApiError();
    apiError.setErrorCode(HttpStatus.NOT_FOUND.value());
    apiError.setErrorMessage("Users not found");
    apiError.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ErrorSavingIntoDBException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ApiError> handleSavingIntoDBException() {
    ApiError apiError = new ApiError();
    apiError.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    apiError.setErrorMessage("Error saving into database");
    apiError.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<ApiError> handleUserAlreadyExistsException() {
    ApiError apiError = new ApiError();
    apiError.setErrorCode(HttpStatus.CONFLICT.value());
    apiError.setErrorMessage("User already exists with that email");
    apiError.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(WrongEmailOrPasswordException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<ApiError> handleWrongEmailOrPasswordException() {
    ApiError apiError = new ApiError();
    apiError.setErrorCode(HttpStatus.UNAUTHORIZED.value());
    apiError.setErrorMessage("Wrong email or password");
    apiError.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(PasswordReusedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ApiError> handlePasswordReusedException() {
    ApiError apiError = new ApiError();
    apiError.setErrorCode(HttpStatus.BAD_REQUEST.value());
    apiError.setErrorMessage("Password already used in the past");
    apiError.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PasswordMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ApiError> handlePasswordMismatchException() {
    ApiError apiError = new ApiError();
    apiError.setErrorCode(HttpStatus.BAD_REQUEST.value());
    apiError.setErrorMessage("New passwords do not match");
    apiError.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }
}