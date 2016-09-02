package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.User;

public interface UserProvider {
    User getUser();

    void setUser(User user);

    boolean authorized();

    boolean isCurrent(long userId);
}
