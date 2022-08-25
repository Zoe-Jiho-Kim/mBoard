package com.project.managing.service;

import com.project.managing.model.Comment;
import com.project.managing.model.Post;
import com.project.managing.model.dto.CommentDto;
import com.project.managing.model.dto.PostDto;
import com.project.managing.repository.CommentRepository;
import com.project.managing.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void write(PostDto postDto) {
        Post post = new Post(postDto);
        postRepository.save(post);
    }

    @Transactional
    public Page<Post> postList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->
                    new IllegalArgumentException("해당 게시물을 찾을 수 없습니다.")
                );
        post.update(postDto);
    }

    @Transactional
    public void commentWrite(int postId, CommentDto commentDto) {
        Comment comment = new Comment(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> {
                    return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 ID를 찾을 수 없습니다.");
                });
        comment.setPost(post);
        commentRepository.save(comment);
    }


    @Transactional
    public void delComment(int postId, int commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        post.getComments();



        commentRepository.deleteById(commentId);
    }
}
