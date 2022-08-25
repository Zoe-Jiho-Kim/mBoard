package com.project.managing.controller.api;

import com.project.managing.model.dto.CommentDto;
import com.project.managing.model.dto.PostDto;
import com.project.managing.model.dto.ResponseDto;
import com.project.managing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostApiController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/post")
    public ResponseDto<Integer> save(@RequestBody PostDto postDto) {
        postService.write(postDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable("id") Integer id) {
        postService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/post/{id}")
    public ResponseDto<Integer> update(@PathVariable("id") Integer id,
                                       @RequestBody PostDto postDto) {
        postService.update(id, postDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/post/{postId}/comment")
    public ResponseDto<Integer> commentSave(@PathVariable("postId") Integer postId,
                                            @RequestBody CommentDto commentDto) {
        postService.commentWrite(postId, commentDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/post/{postId}/comment/{commentId}")
    public ResponseDto<Integer> commentDelete(@PathVariable("postId") Integer postId,@PathVariable("commentId") Integer commentId) {
        postService.delComment(postId, commentId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
