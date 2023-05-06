package todoapp.todo.exeptions;

public class PasswordIsNotPresentedException extends RuntimeException {
    public PasswordIsNotPresentedException(String message) {
        super(message);
    }
}
