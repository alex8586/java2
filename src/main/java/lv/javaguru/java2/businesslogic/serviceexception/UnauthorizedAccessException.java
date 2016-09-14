package lv.javaguru.java2.businesslogic.serviceexception;

/**
 * Created by algis on 16.14.9.
 */
public class UnauthorizedAccessException extends IllegalRequestException {
    public static final String error = "Unauthorized access attempt. Please login first";

    public UnauthorizedAccessException() {
        super(error);
    }
}
