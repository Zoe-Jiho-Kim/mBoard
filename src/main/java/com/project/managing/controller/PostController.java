package com.project.managing.controller;

import com.project.managing.model.Post;
import com.project.managing.repository.PostRepository;
import com.project.managing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;


    @GetMapping( {"","/"})
    public Page<Post> postList(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.postList(pageable);
    }

}
