package exception;

public class EmptyListException extends Exception {
    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException() {

    }
}
