package com.socialnetwork.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialnetwork.demo.dto.User;
import com.socialnetwork.demo.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserControllerEndpointTest {

    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    ObjectMapper jsonMapper;

    @BeforeEach
    void setupEnvironment() {
        userRepository.deleteAll();
    }

    @AfterEach
    void cleanupEnvironment() {
        userRepository.deleteAll();
    }

    @Test
    public void getUserEndpointHappyPass() throws Exception {
        User user = new User("UserName", "123 Rode rode, NY");
        userRepository.save(user);
        List<User> usersFromDb = userRepository.findAll();
        assertEquals(1, usersFromDb.size());

        String url = "http://localhost:" + port + "/users/" + usersFromDb.get(0).getId();
        ResponseEntity<User> response = this.restTemplate.getForEntity(url, User.class);
        assertEquals(200, response.getStatusCodeValue());

        assertEquals(user.getUserName(), response.getBody().getUserName());
        assertEquals(user.getAddress(), response.getBody().getAddress());
    }


    @Test
    public void createNewUserEndpointHappyPass() throws Exception {
        User user = new User("UserName", "123 Rode rode, NY");
        int statusCode = this.restTemplate.postForEntity("http://localhost:" + port + "/users",
                user,
                User.class).getStatusCodeValue();
        assertEquals(200, statusCode);
        List<User> usersFromDb = userRepository.findAll();
        assertEquals(1, usersFromDb.size());
        User userFromDb = usersFromDb.get(0);
        assertEquals(user.getUserName(), userFromDb.getUserName());
        assertEquals(user.getAddress(), userFromDb.getAddress());
        userRepository.deleteAll();
    }

}
