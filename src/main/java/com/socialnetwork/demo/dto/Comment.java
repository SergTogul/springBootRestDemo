package com.socialnetwork.demo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Comment {
    private @Id
    @GeneratedValue
    Long id;
    private String title;
    private String body;
    private Long userId;
    private Long parentPostId;
    private Long parentCommentId;
}
