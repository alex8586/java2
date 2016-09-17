package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Rate;

import java.util.List;

public interface RateDAO extends CrudDAO<Rate>{

    Rate getByUserIdAndProductId(long userId, long productId);

    List<Rate> getByProductId(long productId);
    double getAverageRate(long productId);
}
