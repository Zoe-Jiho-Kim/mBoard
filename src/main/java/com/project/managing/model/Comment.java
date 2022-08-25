package com.project.managing.model;

import com.project.managing.model.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name="postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm")
    private LocalDate createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    public Comment(CommentDto commentDto) {
        this.content = commentDto.getContent();
        this.post = commentDto.getPost();
        this.user = commentDto.getUser();
        this.createDate = commentDto.getCreateDate();
    }

    public void update(CommentDto commentDto) {
        this.content = commentDto.getContent();
        this.createDate = commentDto.getCreateDate();
    }
}
