package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.User;

public interface CurrentUser {
    User getUser();

    void setUser(User user);

    boolean authorized();
}
