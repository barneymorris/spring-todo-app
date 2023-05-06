package todoapp.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoapp.todo.entity.Posts;
import todoapp.todo.repository.PostsRepository;

import java.util.List;

@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Autowired
    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public List<Posts> getPosts() {
        return (List<Posts>) postsRepository.findAll();
    }
}
