package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.crossdomain.StockProduct;
import lv.javaguru.java2.database.StockDAO;
import lv.javaguru.java2.database.StockProductDAO;
import lv.javaguru.java2.domain.Stock;
import lv.javaguru.java2.helpers.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class StockEditorServiceImpl implements StockEditorService {

    private final static String WRONG_NUMBER_FORMAT = "Quantity must be numbers";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH);
    private final String EMPTY_FIELDS = "All fields must be filled";
    @Autowired
    private StockProductDAO stockProductDAO;
    @Autowired
    private StockDAO stockDAO;
    @Autowired
    private TemplateService templateService;

    @Override
    public Map<String, Object> getStockList() {
        Map<String, Object> map = new HashMap<>();
        List<StockProduct> stockProducts = stockProductDAO.getAll();
        map.putAll(templateService.model());
        map.put("stockProducts", stockProducts);
        return map;
    }

    @Override
    public void updateStock(long stockId, String quantity, String expireDate) throws ServiceException {
        int amount;
        if(quantity.equals("") || quantity.isEmpty())
            throw new WrongFieldFormatException(EMPTY_FIELDS);
        try{
            amount  = Integer.parseInt(quantity);
        }catch (NumberFormatException e){
            throw new ServiceException(WRONG_NUMBER_FORMAT);
        }
        LocalDate date = DateUtils.asLocalDate(expireDate, formatter);
        Stock stock = stockDAO.getById(stockId);
        stock.setQuantity(amount);
        stock.setExpireDate(DateUtils.asDate(date));
        stockDAO.update(stock);
    }
}
