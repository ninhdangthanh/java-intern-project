package com.study.backend.service;

import com.study.backend.entity.User;
import com.study.backend.exception.BadRequestException;
import com.study.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(@Valid User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new BadRequestException("Email " + user.getEmail() + " taken");
        }
        if(user.getPassword().length() < 4 && user.getPassword().length() > 12) {
            throw new BadRequestException("Password invalid!!!");
        }
        userRepository.save(user);
        return user;
    }

    public void updateUser(User user) {
        // Perform any necessary validation, such as checking for existing email address
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}