package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.domain.CountUser;
import lv.javaguru.java2.domain.CountVisitor;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
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

    @Autowired
    private UserProvider userProvider;
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

    @Override
    public void countVisit(Product product){
        if(userProvider.getUser() != null){
            if(visitedProduct.contains(product.getId())){
                return;
            }
            visitedProduct.add(product.getId());
            User user = userProvider.getUser();
            CountUser countUser = countUsersDAO.getCountUserByUserIdProductId(user.getId(), product.getId());
            if(countUser != null){
                countUser.setCounter(countUser.getCounter() + 1);
                countUsersDAO.update(countUser);
            } else {
                countUser = new CountUser();
                countUser.setUserId(user.getId());
                countUser.setProductId(product.getId());
                countUser.setCounter(1);
                countUsersDAO.create(countUser);
            }
        }
    }

    @Override
    public void countVisit(Product product, String ip){
        if(visitedProduct.contains(product.getId())){
            return;
        }
        visitedProduct.add(product.getId());
        CountVisitor countVisitor = countVisitorsDAO.getCountUserByUserIpProductId(ip, product.getId());
        if(countVisitor != null){
            countVisitor.setCounter(countVisitor.getCounter() + 1);
            countVisitorsDAO.update(countVisitor);
        } else {
            countVisitor = new CountVisitor();
            countVisitor.setIp(ip);
            countVisitor.setProductId(product.getId());
            countVisitor.setCounter(1);
            countVisitorsDAO.create(countVisitor);
        }
    }
}
