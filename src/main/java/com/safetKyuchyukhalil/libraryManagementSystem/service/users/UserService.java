package com.safetKyuchyukhalil.libraryManagementSystem.service.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.User;
import com.safetKyuchyukhalil.libraryManagementSystem.exception.UserNotFoundException;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
