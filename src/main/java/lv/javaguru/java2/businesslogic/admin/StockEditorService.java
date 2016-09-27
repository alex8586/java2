package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;

import java.util.Map;

public interface StockEditorService {

    Map<String, Object> getStockList();

    void updateStock(long productId, String quantity, String expireDate) throws ServiceException;
}
