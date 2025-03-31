package exception;

public class ValidationException extends Exception{
    public ValidationException(String message) {
        super("Invalid input, please try again" + message);
    }
}
