package com.project.managing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.managing.model.dto.PostDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    private String isEnded;

    private String managerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"post"})
    @OrderBy("id desc")
    private List<Comment> comments;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm")
    private LocalDate createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    public Post(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.isEnded = postDto.getIsEnded();
        this.managerId = postDto.getManagerId();
        this.user = postDto.getUser();
        this.comments = postDto.getComments();
        this.createDate = postDto.getCreateDate();
    }

    public void update(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.isEnded = postDto.getIsEnded();
        this.managerId = postDto.getManagerId();
        this.createDate = postDto.getCreateDate();
    }
}
