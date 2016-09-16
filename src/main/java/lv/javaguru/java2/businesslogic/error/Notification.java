package lv.javaguru.java2.businesslogic.error;


public interface Notification {
    boolean haveError();

    boolean haveMessage();

    boolean haveNotification();

    String getError();

    void setError(String error);

    String getMessage();

    void setMessage(String message);
}
