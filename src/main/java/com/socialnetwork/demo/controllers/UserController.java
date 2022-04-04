package com.socialnetwork.demo.controllers;

import com.socialnetwork.demo.dto.User;
import com.socialnetwork.demo.exceptions.badRequest.MissedFieldException;
import com.socialnetwork.demo.exceptions.notFound.UserNotFoundException;
import com.socialnetwork.demo.repositories.PostRepository;
import com.socialnetwork.demo.repositories.UserRepository;
import com.socialnetwork.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User newUser) {
        if (null == newUser.getUserName() || newUser.getUserName().trim().isEmpty()) {
            throw new MissedFieldException("userName");
        }
        newUser.setTimestamp(System.currentTimeMillis());
        return userRepository.save(newUser);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User newUser) {
        Utils.verifyId(userRepository, newUser.getId());
        newUser.setTimestamp(System.currentTimeMillis());
        User oldUser = userRepository.findById(newUser.getId()).get();
        User updatedUser = Utils.updateUserData(oldUser, newUser);
        return userRepository.save(updatedUser);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@RequestParam Long userId) {
        Utils.verifyId(userRepository, userId);
        userRepository.deleteById(userId);
    }
}