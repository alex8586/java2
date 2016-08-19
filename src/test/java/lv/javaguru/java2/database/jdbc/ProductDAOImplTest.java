package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.BaseEntityTest;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ProductDAOImplTest extends BaseEntityTest<Product, ProductDAOImpl> {

    private Category category = new Category();
    private Category anotherCategory = new Category();

    @Override
    protected void initDAO() {
        dao = new ProductDAOImpl();
    }

    @Override
    protected Product newRecord() {
        return new Product();
    }

    @Override
    protected void compareRecords(Product product1, Product product2) {
        assertEquals(product1.getId(), product2.getId());
        assertEquals(product1.getVendorCode(), product2.getVendorCode());
        assertEquals(product1.getPrice(), product2.getPrice());
        assertEquals(product1.getVendorCode(), product2.getVendorCode());
        assertEquals(product1.getDisplayDescription(), product2.getDisplayDescription());
        assertEquals(product1.getCategoryID(), product2.getCategoryID());
        assertEquals(product1.getRemainQty(), product2.getRemainQty());
        assertEquals(product1.getVendorName(), product2.getVendorName());
        assertEquals(product1.getDisplayName(), product2.getDisplayName());
    }

    @Override
    protected void fillRecordWithData(Product product) {
        product.setCategoryID(category.getId());
        product.setPrice(random.nextInt(100000));
        product.setDisplayName("display name" + random.nextInt(100000));
        product.setRemainQty(random.nextInt(100000));
        product.setUnit("parrots" + random.nextInt(100000));
        product.setDisplayDescription("foo bar" + random.nextInt(100000));
        product.setVendorName("vendor name" + random.nextInt(100000));
        product.setVendorCode("v" + random.nextInt(100000));
        product.setVendorDescription("vendor description" + random.nextInt(100000));
    }

    @Override
    protected void makeChangesInRecord(Product product) {
        product.setPrice(product.getPrice() + 1);
        product.setDisplayName(product.getDisplayName() + "prim");
        product.setRemainQty(product.getRemainQty() + 1);
        product.setUnit("elephants" + random.nextInt(100000));
        product.setDisplayDescription("booz" + product.getDisplayDescription());
        product.setVendorName("zz" + product.getVendorName());
        product.setVendorCode("code" + random.nextInt(100000));
        product.setVendorDescription("desc" + product.getVendorDescription());
        product.setCategoryID(anotherCategory.getId());
    }

    @Before
    @Override
    public void before() {
        cleaner.cleanDatabase();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        category.setName("category");
        categoryDAO.create(category);
        anotherCategory.setName("another category");
        categoryDAO.create(anotherCategory);
        super.before();
    }


    @Test
    public void getByVendorCodeTest() {
        Product product = dao.getByVendorCode(recordFromDAO.getVendorCode());
        compareRecords(recordFromDAO, product);
    }

    @Test
    public void badVendorCodeReturnNull() {
        assertNull(dao.getByVendorCode("asdasasda"));
    }

    @Test
    public void getByCategory() {
        assertEquals(0, dao.getAllByCategory(anotherCategory).size());
        assertEquals(1, dao.getAllByCategory(category).size());

        Product anotherProduct = new Product();
        fillRecordWithData(anotherProduct);
        anotherProduct.setVendorCode("unique");
        dao.create(anotherProduct);
        assertEquals(2, dao.getAllByCategory(category).size());
    }

    @Test
    public void getAllAvailable() {
        assertEquals(1, dao.getAllAvailable().size());
        Product product = new Product();
        fillRecordWithData(product);
        product.setRemainQty(0);
        dao.create(product);
        assertEquals(1, dao.getAllAvailable().size());
        product.setRemainQty(2);
        dao.update(product);
        assertEquals(2, dao.getAllAvailable().size());
    }

}