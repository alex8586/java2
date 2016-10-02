package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SessionUserProvider implements UserProvider {

    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean authorized() {
        return user != null;
    }
    public boolean isCurrent(long userId) {
        return authorized() && this.user.getId() == userId;
    }
}
