package Exceptions;

/**
 * Created by Vik on 3/14/2016.
 */
public class RegisterError extends Exception {
    String message;

    public RegisterError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
