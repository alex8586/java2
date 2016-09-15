package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Rate;

public interface RateDAO extends CrudDAO<Rate>{

    Rate getByUserIdAndProductId(long userId, long productId);

    double getAverageRate(long productId);
}
