package com.socialnetwork.demo.controllers;

import com.socialnetwork.demo.dto.Post;
import com.socialnetwork.demo.dto.User;
import com.socialnetwork.demo.repositories.PostRepository;
import com.socialnetwork.demo.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Profile("test")
public class UserControllerUnitWithMockTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private PostRepository postRepository = Mockito.mock(PostRepository.class);

    private UserController userController = new UserController(userRepository, postRepository);

    @BeforeEach
    void setupEnvironment() {
        userRepository.deleteAll();
    }

    @AfterEach
    void cleanupEnvironment() {
        userRepository.deleteAll();
    }

//    @Test
//    public void findByUserNameWithPrefixHappyPath() {
//        User user = new User("UserName", "123 Rode rode, NY", null);
//        userRepository.save(user);
//        List<User> usersFromDb = userController.findByUserNameWithPrefix("User");
//        assertEquals(1, usersFromDb.size());
//        User userFromDb = usersFromDb.get(0);
//        assertEquals(user.getUserName(), userFromDb.getUserName());
//        assertEquals(user.getAddress(), userFromDb.getAddress());
//        userRepository.deleteAll();
//    }
//
//    @Test
//    public void findByUserNameWithPrefixNegative() {
//        User user = new User("UserName", "123 Rode rode, NY", null);
//        userRepository.save(user);
//        List<User> usersFromDb = userController.findByUserNameWithPrefix("UserA");
//        assertEquals(0, usersFromDb.size());
//        userRepository.deleteAll();
//    }

    @Test
    public void findByUserIdWithMocks() {
        User user = new User("UserName", "123 Rode rode, NY");
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        User userFromDb = userController.findUser(1L);
        assertEquals(user.getUserName(), userFromDb.getUserName());
        assertEquals(user.getAddress(), userFromDb.getAddress());
    }



}
