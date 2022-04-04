package com.socialnetwork.demo.controllers;

import com.socialnetwork.demo.dto.Post;
import com.socialnetwork.demo.exceptions.notFound.PostNotFoundException;
import com.socialnetwork.demo.exceptions.notFound.UserPostNotFoundException;
import com.socialnetwork.demo.repositories.PostRepository;
import com.socialnetwork.demo.repositories.UserRepository;
import com.socialnetwork.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/posts")
    public Page<Post> findPostsFromUser(@RequestParam Long userId, @RequestParam(required = false) Integer page) {
        if (null == page || page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<Post> posts = postRepository.findByUserId(userId, pageable);
        if (0 == posts.getTotalElements()) {
            throw new UserPostNotFoundException(userId);
        }
        return posts;
    }

    @GetMapping("/posts/{id}")
    public Post findPost(@PathVariable Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post newPost) {
        Utils.verifyId(postRepository, newPost.getUserId());
        newPost.setTimestamp(System.currentTimeMillis());
        return postRepository.save(newPost);
    }

    @DeleteMapping("/posts")
    public void deletePost(@PathVariable Long postId) {
        Utils.verifyId(postRepository, postId);
        postRepository.deleteById(postId);
    }
}