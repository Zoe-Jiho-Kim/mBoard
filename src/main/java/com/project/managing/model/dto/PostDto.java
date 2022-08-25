package com.project.managing.model.dto;

import com.project.managing.model.Comment;
import com.project.managing.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private String isEnded;
    private String managerId;
    private User user;
    private List<Comment> comments;
    private LocalDate createDate;
}
