package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountVisitor;

import java.util.List;

public interface CountVisitorsDAO extends CrudDAO<CountVisitor> {

    long getCountByProductId(long productId);
    long getCountByIp(String ip);
    long getSumCountFromAllTable();
    List getAllCount();
    CountVisitor getCountUserByUserIpProductId(String ip, long productId);

}
