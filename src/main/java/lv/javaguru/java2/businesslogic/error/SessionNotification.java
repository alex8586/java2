package lv.javaguru.java2.businesslogic.notification;

import lv.javaguru.java2.businesslogic.error.Notification;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SessionNotification implements Notification {

    boolean isError = false;
    boolean isMessage = false;

    String error = "";
    String message = "";

    public boolean haveError() {
        return isError;
    }

    public boolean haveMessage() {
        return isMessage;
    }

    public boolean haveNotification() {
        return isError || isMessage;
    }

    public String getError() {
        String error = this.error;
        isError = false;
        this.error = "";
        return error;
    }

    public void setError(String error) {
        isError = true;
        isMessage = false;
        this.error = error;
        this.message = "";
    }

    public String getMessage() {
        String message = this.message;
        isMessage = false;
        this.message = "";
        return message;
    }

    public void setMessage(String message) {
        isError = false;
        isMessage = true;
        this.message = message;
        this.error = "";
    }
}
