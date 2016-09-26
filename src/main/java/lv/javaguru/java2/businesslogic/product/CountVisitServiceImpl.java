package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.database.CrudDAO;
import lv.javaguru.java2.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
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

    private void countVisit(Product product, CountEntity countEntity, CrudDAO countDAO) {
        if (visitedProduct.contains(product.getId())) {
            return;
        }
        visitedProduct.add(product.getId());
        countEntity.setCounter(countEntity.getCounter() + 1);
        if (countEntity != null) {
            countDAO.update(countEntity);
        } else {
            countEntity.setProductId(product.getId());
            countDAO.create(countEntity);
        }
    }

    @Override
    public void countVisit(Product product, User user) {
        CountUser count = countUsersDAO.getCountUserByUserIdProductId(user.getId(), product.getId());
        if (count == null) {
            count = new CountUser();
            count.setUserId(user.getId());
        }
        countVisit(product, count, countUsersDAO);
    }

    @Override
    public void countVisit(Product product, String ip){
        CountVisitor count = countVisitorsDAO.getCountUserByUserIpProductId(ip, product.getId());
        if (count == null) {
            count = new CountVisitor();
            count.setIp(ip);
        }
        countVisit(product, count, countUsersDAO);
    }
}
