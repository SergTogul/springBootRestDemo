package com.socialnetwork.demo.repositories;

import com.socialnetwork.demo.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userName like ?1%")
    List<User> findByNamePrefix(String prefix);
}
