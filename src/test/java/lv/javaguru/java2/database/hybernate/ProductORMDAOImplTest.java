package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class ProductORMDAOImplTest extends CrudHybernateDAOTest<Product, ProductORMDAOImpl> {

    @Autowired
    CategoryORMDAOImpl categoryDAO;

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
        assertEquals(product1.getCategoryId(), product2.getCategoryId());
    }

    @Override
    protected void fillRecordWithData(Product product) {
        product.setName("name" + random.nextInt(100000));
        product.setDescription("description" + random.nextInt(100000));
        product.setPrice(random.nextInt(100000));
        product.setCategoryId(category.getId());
        product.setImgUrl("imgpath");
    }

    @Override
    protected void makeChangesInRecord(Product product) {
        product.setPrice(product.getPrice() + 1);
        product.setName(product.getName() + "prim");
        product.setDescription("booz" + product.getDescription());
        product.setCategoryId(anotherCategory.getId());
    }

    @Before
    @Override
    public void before() {
        cleaner.cleanDatabase();
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

}
