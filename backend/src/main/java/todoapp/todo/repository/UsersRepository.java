package todoapp.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todoapp.todo.entity.Posts;
import todoapp.todo.entity.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
