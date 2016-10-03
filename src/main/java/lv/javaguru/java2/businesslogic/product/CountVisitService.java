package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.domain.User;

public interface CountVisitService {

    void countVisit(long id, User user);

    void countVisit(long id, String ip);
}
