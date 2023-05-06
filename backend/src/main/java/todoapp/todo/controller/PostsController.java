package todoapp.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import todoapp.todo.dto.PostDTO;
import todoapp.todo.entity.Posts;
import todoapp.todo.service.PostsService;

import java.util.List;

@Controller
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/api/posts")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Posts>> getPosts() {
        return new ResponseEntity<>(postsService.getPosts(), HttpStatus.OK);
    }

    @PostMapping("/api/posts")
    public ResponseEntity<List<Posts>> setPost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postsService.setPost(postDTO), HttpStatus.OK);
    }
}
