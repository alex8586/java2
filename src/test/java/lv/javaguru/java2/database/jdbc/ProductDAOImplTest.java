package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ProductDAOImplTest {

    private ProductDAOImpl productDAO = new ProductDAOImpl();
    private DatabaseCleaner cleaner = new DatabaseCleaner();
    private Category category;


    @Before
    public void before() throws DBException{
        cleaner.cleanDatabase();
        CategoryDAO dao = new CategoryDAO();
        category = new Category();
        category.setName("cat for prod");
        dao.createWithId(category);
    }

    @Test
    public void testCanFindCreatedRecord() throws DBException{
        Product product = newProduct();
        productDAO.createProduct(product);
        assertTrue(product.getId() > 0 );
        Product newProduct = productDAO.getProductByID(product.getId());
        assertNotNull(newProduct);

        assertEquals(product.getCategoryID() , newProduct.getCategoryID());
        assertEquals(product.getDisplayDescription() , newProduct.getDisplayDescription());
        assertEquals(product.getDisplayName() , newProduct.getDisplayName());
        assertEquals(product.getVendorDescription() , newProduct.getVendorDescription());
    }

    @Test
    public void testCanSeeUpdatesAfterUpdate() throws DBException{
        Product product = newProduct();
        productDAO.createProduct(product);
        Product newProduct = productDAO.getProductByID(product.getId());

        newProduct.setPrice(234);
        newProduct.setDisplayName("newName");
        productDAO.updateProduct(newProduct);

        Product yetAnotherProduct = productDAO.getProductByID(product.getId());
        assertEquals(newProduct.getPrice() , yetAnotherProduct.getPrice());
        assertEquals(newProduct.getDisplayName(),yetAnotherProduct.getDisplayName());
    }

    @Test
    public void testCantFindDeletedRecord() throws DBException{
        Product product = newProduct();
        productDAO.createProduct(product);
        Product newProduct = productDAO.getProductByID(product.getId());
        productDAO.deleteProduct(newProduct);
        assertEquals(0 , newProduct.getId());

        Product yetAnotherProduct = productDAO.getProductByID(product.getId());
        assertNull(yetAnotherProduct);
    }

    private Product newProduct(){
        Product product = new Product();
        product.setCategoryID(category.getId());
        product.setPrice(123);
        product.setDisplayName("display name");
        product.setRemainQty(123);
        product.setUnit("parrots");
        product.setDisplayDescription("foo bar");
        product.setVendorName("vendor name");
        product.setVendorCode("vendor");
        product.setVendorDescription("vendor description");
        return product;
    }
}