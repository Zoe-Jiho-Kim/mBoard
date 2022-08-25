package com.project.managing.model.dto;

import com.project.managing.model.Post;
import com.project.managing.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private String content;
    private Post post;
    private User user;
    private LocalDate createDate;
}
