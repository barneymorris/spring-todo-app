package todoapp.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todoapp.todo.entity.Posts;

@Repository
public interface PostsRepository extends CrudRepository<Posts, Long> {
}
