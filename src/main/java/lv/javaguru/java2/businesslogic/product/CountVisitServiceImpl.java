package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.database.CrudDAO;
import lv.javaguru.java2.domain.CountEntity;
import lv.javaguru.java2.domain.CountUser;
import lv.javaguru.java2.domain.CountVisitor;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CountVisitServiceImpl implements CountVisitService {

    @Qualifier("ORM_CountVisitorsDAO")
    @Autowired
    private CountVisitorsDAO countVisitorsDAO;
    @Qualifier("ORM_CountUsersDAO")
    @Autowired
    private CountUsersDAO countUsersDAO;
    private List<Long> visitedProduct;

    public CountVisitServiceImpl(){
        this.visitedProduct = new LinkedList<>();
    }

    private void countVisit(long productId, CountEntity countEntity, CrudDAO countDAO) {
        if (visitedProduct.contains(productId)) {
            return;
        }
        visitedProduct.add(productId);
        countEntity.setCounter(countEntity.getCounter() + 1);
        if (countEntity.getId() > 0) {
            countDAO.update(countEntity);
        } else {
            countEntity.setProductId(productId);
            countDAO.create(countEntity);
        }
    }

    @Override
    public void countVisit(long productId, User user) {
        CountUser count = countUsersDAO.getCountUserByUserIdProductId(user.getId(), productId);
        if (count == null) {
            count = new CountUser();
            count.setUserId(user.getId());
        }
        countVisit(productId, count, countUsersDAO);
    }

    @Override
    public void countVisit(long productId, String ip) {
        CountVisitor count = countVisitorsDAO.getCountUserByUserIpProductId(ip, productId);
        if (count == null) {
            count = new CountVisitor();
            count.setIp(ip);
        }
        countVisit(productId, count, countVisitorsDAO);
    }
}
