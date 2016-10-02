package lv.javaguru.java2.businesslogic.notification;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SessionNotification implements Notification {

    String error = "";
    String message = "";
    private boolean isError = false;
    private boolean isMessage = false;

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

    public void setError(Exception e) {
        setError(e.getMessage());
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
