package todoapp.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<Posts>> getPosts() {
        return new ResponseEntity<>(postsService.getPosts(), HttpStatus.OK);
    }
}
