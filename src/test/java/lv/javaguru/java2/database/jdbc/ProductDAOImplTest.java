package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class ProductDAOImplTest {

    private ProductDAOImpl productDAO = new ProductDAOImpl();
    private DatabaseCleaner cleaner = new DatabaseCleaner();
    private Category category;

    @Before
    public void before() throws DBException{
        cleaner.cleanDatabase();
        CategoryDAOImpl dao = new CategoryDAOImpl();
        category = new Category();
        category.setName("category for products");
        dao.create(category);
    }

    @Test
    public void testFindWithWrongParamReturnsNull() throws DBException{
        Product product = productDAO.getById(-1);
        assertNull(product);
        product = productDAO.getByVendorCode("thisisnotexistingvendorcodebecausedbwillnotallowsuchlongvendorcode");
        assertNull(product);
    }

    @Test
    public void testCanFindCreatedRecord() throws DBException{
        Product product = newProduct();
        productDAO.create(product);
        assertTrue(product.getId() > 0 );
        Product newProduct = productDAO.getById(product.getId());
        assertNotNull(newProduct);

        assertEquals(product.getCategoryID() , newProduct.getCategoryID());
        assertEquals(product.getDisplayDescription() , newProduct.getDisplayDescription());
        assertEquals(product.getDisplayName() , newProduct.getDisplayName());
        assertEquals(product.getVendorDescription() , newProduct.getVendorDescription());
    }

    @Test
    public void getProductByIdTest() throws DBException {
        Product product = newProduct();
        long idFromDAO = productDAO.create(product);
        Product productFromDAO = productDAO.getById(idFromDAO);
        assertEquals(product.getPrice(), productFromDAO.getPrice());
        assertEquals(product.getVendorCode(), productFromDAO.getVendorCode());
        assertEquals(product.getDisplayDescription(), productFromDAO.getDisplayDescription());
        assertEquals(product.getCategoryID(), productFromDAO.getCategoryID());
        assertEquals(product.getRemainQty(), productFromDAO.getRemainQty());
        assertEquals(product.getVendorName(), productFromDAO.getVendorName());
        assertEquals(product.getDisplayName(), productFromDAO.getDisplayName());
    }

    @Test
    public void testCanSeeUpdatesAfterUpdate() throws DBException{
        Product product = newProduct();
        productDAO.create(product);
        Product newProduct = productDAO.getById(product.getId());

        newProduct.setPrice(234);
        newProduct.setDisplayName("newName");
        productDAO.update(newProduct);

        Product yetAnotherProduct = productDAO.getById(product.getId());
        assertEquals(newProduct.getPrice() , yetAnotherProduct.getPrice());
        assertEquals(newProduct.getDisplayName(),yetAnotherProduct.getDisplayName());
    }

    @Test
    public void testCantFindDeletedRecord() throws DBException{
        Product product = newProduct();
        productDAO.create(product);
        Product newProduct = productDAO.getById(product.getId());
        productDAO.delete(newProduct);
        assertEquals(0 , newProduct.getId());

        Product yetAnotherProduct = productDAO.getById(product.getId());
        assertNull(yetAnotherProduct);
    }

    @Test
    public void getNewEmptyProductTest() throws DBException {
        Product product = productDAO.getNewEmptyProduct();
        assertTrue(product.getId() > 0);
        assertNull(product.getUnit());
        assertTrue(product.getCategoryID() == 0);
        assertTrue(product.getPrice() == 0);
        assertTrue(product.getRemainQty() == 0);
        assertNull(product.getDisplayDescription());
    }

    @Test
    public void getAllProductsTest() throws DBException {
        List<Product> productList = productDAO.getAll();
        assertTrue(0 == productList.size());

        Product firstProduct = newProduct();
        Product secondProduct = newSecondProduct();
        productDAO.create(firstProduct);
        productDAO.create(secondProduct);

        productList = productDAO.getAll();
        assertTrue(2 == productList.size());

        productDAO.delete(firstProduct);
        productList = productDAO.getAll();
        assertTrue(1 == productList.size());
    }

    @Test
    public void getProductByVendorCodeTest() throws DBException {
        Product product = newProduct();
        productDAO.create(product);

        Product productByVendor = productDAO.getByVendorCode(product.getVendorCode());
        Product productById     = productDAO.getById(product.getId());

        assertNotNull(productByVendor);
        assertNotNull(productById);
        assertEquals(productById.getId() , productByVendor.getId());
        assertEquals(productById.getVendorCode() , productByVendor.getVendorCode());

        assertEquals(productById.getPrice(), productByVendor.getPrice());
        assertEquals(productById.getVendorCode(), productByVendor.getVendorCode());
        assertEquals(productById.getDisplayDescription(), productByVendor.getDisplayDescription());
        assertEquals(productById.getCategoryID(), productByVendor.getCategoryID());
        assertEquals(productById.getRemainQty(), productByVendor.getRemainQty());
        assertEquals(productById.getVendorName(), productByVendor.getVendorName());
        assertEquals(productById.getDisplayName(), productByVendor.getDisplayName());
    }


    @Test
    public void getProductByCategory() throws DBException {
        CategoryDAOImpl dao = new CategoryDAOImpl();
        Category otherCategory = new Category();
        otherCategory.setName("another category for products");
        dao.create(otherCategory);

        productDAO.create(newProduct());
        productDAO.create(newSecondProduct());

        Product otherCategoryProduct = newProduct();
        otherCategoryProduct.setCategoryID(otherCategory.getId());
        otherCategoryProduct.setVendorCode("other");
        productDAO.create(otherCategoryProduct);

        List<Product> productList;
        productList = productDAO.getAll();
        assertTrue(3 == productList.size());

        productList = productDAO.getByCategory(category);
        assertTrue(2 == productList.size());

        productList = productDAO.getByCategory(otherCategory);
        assertTrue(1 == productList.size());

        productDAO.delete(otherCategoryProduct);
        productList = productDAO.getByCategory(otherCategory);
        assertTrue(0 == productList.size());
    }


    private Product newProduct(){
        Product product = new Product();
        product.setCategoryID(category.getId());
        product.setPrice(123);
        product.setDisplayName("display name");
        product.setRemainQty(67);
        product.setUnit("parrots");
        product.setDisplayDescription("foo bar");
        product.setVendorName("vendor name");
        product.setVendorCode("vendor");
        product.setVendorDescription("vendor description");
        return product;
    }

    private Product newSecondProduct(){
        Product product = new Product();
        product.setCategoryID(category.getId());
        product.setPrice(567);
        product.setDisplayName("new name");
        product.setRemainQty(45);
        product.setUnit("apple");
        product.setDisplayDescription("new description");
        product.setVendorName("new vendor name");
        product.setVendorCode("new code");
        product.setVendorDescription("new vendor description");
        return product;
    }

}