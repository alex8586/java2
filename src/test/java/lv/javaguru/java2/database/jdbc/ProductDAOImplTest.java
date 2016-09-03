package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.helpers.CategoryTree;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class ProductDAOImplTest extends CrudJdbcDAOTest<Product, ProductDAOImpl> {

    @Mock
    CategoryTree categoryTree;

    private Category category = new Category();
    private Category anotherCategory = new Category();

    @Override
    protected Product newRecord() {
        return new Product();
    }

    @Override
    protected void compareRecords(Product product1, Product product2) {
        assertEquals(product1.getId(), product2.getId());
        assertEquals(product1.getName(), product2.getName());
        assertEquals(product1.getDescription(), product2.getDescription());
        assertEquals(product1.getPrice(), product2.getPrice());
        assertEquals(product1.getImgUrl(), product2.getImgUrl());
        assertEquals(product1.getCategoryId(), product2.getCategoryId());
    }

    @Override
    protected void fillRecordWithData(Product product) {
        product.setName("name" + random.nextInt(100000));
        product.setDescription("description" + random.nextInt(100000));
        product.setPrice(random.nextInt(100000));
        product.setImgUrl("pic");
        product.setCategoryId(category.getId());
    }

    @Override
    protected void makeChangesInRecord(Product product) {
        product.setPrice(product.getPrice() + 1);
        product.setName(product.getName() + "prim");
        product.setDescription("booz" + product.getDescription());
        product.setCategoryId(anotherCategory.getId());
        product.setImgUrl("img");
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
    public void getByCategory() {
        assertEquals(0, dao.getAllByCategory(anotherCategory).size());
        assertEquals(1, dao.getAllByCategory(category).size());

        Product anotherProduct = new Product();
        fillRecordWithData(anotherProduct);
        anotherProduct.setName("unique");
        dao.create(anotherProduct);
        assertEquals(2, dao.getAllByCategory(category).size());
    }

    @Test
    public void getRandomProduct() {
        Product product = dao.getRandomProduct();
        assertEquals(product, recordFromDAO);
    }

    @Test
    public void testGetByCategoryTree() {
        Category category = new Category();
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(new ArrayList<Category>()).when(categoryTree).getAncestors(category);
        dao.getByCategoryTree(category);
    }

}