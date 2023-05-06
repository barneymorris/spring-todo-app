package todoapp.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoapp.todo.dto.PostDTO;
import todoapp.todo.entity.Posts;
import todoapp.todo.entity.Users;
import todoapp.todo.repository.PostsRepository;
import todoapp.todo.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public PostsService(PostsRepository postsRepository, UsersRepository usersRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
    }

    public List<Posts> getPosts() {
        return (List<Posts>) postsRepository.findAll();
    }

    public List<Posts> setPost(PostDTO postDTO) {
        Optional<Users> foundedUser = usersRepository
                .findById(Long.valueOf(postDTO.getUserId()));


        Posts newPost = Posts.builder()
                .postText(postDTO.getText())
                .postTitle(postDTO.getTitle())
                .user(foundedUser.get())
                .build();

        postsRepository.save(newPost);

        return (List<Posts>) postsRepository.findAll();
    }
}
