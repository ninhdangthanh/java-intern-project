package com.study.backend.controller;

import com.study.backend.exception.ForbiddenException;
import com.study.backend.user.User;
import com.study.backend.exception.BadRequestException;
import com.study.backend.exception.NotFoundException;
import com.study.backend.request.PasswordReset;
import com.study.backend.request.ResponseData;
import com.study.backend.service.UserService;
import com.study.backend.user.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
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

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ResponseData<String>> handleForbiddenException(NotFoundException ex) {
        ResponseData<String> response = new ResponseData<>("Error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        ResponseData<List<UserDTO>> response = new ResponseData<>("Success", users);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseData<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.createUser(userDTO);
        ResponseData<UserDTO> response = new ResponseData<>("Create success", newUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/resetpassword/{id}")
    public ResponseEntity<ResponseData<UserDTO>> changePassword(@Valid @RequestBody PasswordReset passwordReset, @PathVariable Long id) {
        UserDTO userDTO = userService.updatePassword(id, passwordReset);
        ResponseData<UserDTO> response = new ResponseData<>("Success", userDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserDTOById(id);
        ResponseData<UserDTO> response = new ResponseData<>("Success", userDTO);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseData<UserDTO>> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.getUserDTOByEmail(email);
        ResponseData<UserDTO> response = new ResponseData<>("Success", userDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<UserDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        userService.updateUser(userDTO, id);
        ResponseData<UserDTO> response = new ResponseData<>("Edit success", userDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        ResponseData<String> response = new ResponseData<>("Delete success", "Delete user with id = " + id);
        return ResponseEntity.ok(response);
    }
}