package Exceptions;

/**
 * Created by Vik on 3/14/2016.
 */
public class Error extends Exception {
    String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
