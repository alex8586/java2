package lv.javaguru.java2.businesslogic.notification;


public interface Notification {
    boolean haveError();
    boolean haveMessage();
    boolean haveNotification();

    String getError();

    void setError(Exception e);

    void setError(String error);

    String getMessage();

    void setMessage(String message);
}
