package com.study.backend.controller;

import com.study.backend.entity.User;
import com.study.backend.exception.BadRequestException;
import com.study.backend.exception.NotFoundException;
import com.study.backend.request.PasswordReset;
import com.study.backend.request.ResponseData;
import com.study.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseData<String>> handleBadRequestException(BadRequestException ex) {
        ResponseData<String> response = new ResponseData<>("Error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseData<String>> handleNotFoundException(NotFoundException ex) {
        ResponseData<String> response = new ResponseData<>("Error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<User>>> getAllUser() {
        List<User> users = userService.getAllUser();
        ResponseData<List<User>> response = new ResponseData<>("Success", users);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseData<User>> createUser(@Valid @RequestBody User user) {
        User newUser = userService.createUser(user);
        ResponseData<User> response = new ResponseData<>("Success", newUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/resetpassword/{id}")
    public ResponseEntity<User> changePassword(@Valid @RequestBody PasswordReset passwordReset, @PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.updatePassword(user, passwordReset);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        // Perform any necessary validation, such as checking for existing email address
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}