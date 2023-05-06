package todoapp.todo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotPresentedException extends RuntimeException {
    public EmailNotPresentedException(String message) {
        super(message);
    }
}
