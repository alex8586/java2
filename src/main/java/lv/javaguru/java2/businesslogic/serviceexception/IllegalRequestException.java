package lv.javaguru.java2.businesslogic.serviceexception;


public class IllegalRequestException extends ServiceException {
    private static final String error = "Bad request format.Not all parameters available";

    public IllegalRequestException() {
        super(error);
        this.printStackTrace();
    }
}
