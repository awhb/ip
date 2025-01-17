package chadbod;

/**
 * The TaskIndexOutOfBoundsException class is a custom exception class for handling task index out-of-bounds exceptions
 * in the ChadBod application.
 */
public class TaskIndexOutOfBoundsException extends ChadBodException {
    public TaskIndexOutOfBoundsException() {
        super("Invalid task index.");
    }
}
