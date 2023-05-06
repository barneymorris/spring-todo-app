package todoapp.todo.exeptions;

public class WrongUsernameException extends RuntimeException {
    public WrongUsernameException (String message) {
        super(message);
    }
}
