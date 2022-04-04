package com.socialnetwork.demo.repositories;

import com.socialnetwork.demo.dto.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
