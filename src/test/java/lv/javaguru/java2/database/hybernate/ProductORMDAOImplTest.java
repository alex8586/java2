package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProductORMDAOImplTest extends CrudHybernateDAOTest<Product, ProductORMDAOImpl> {

    @Qualifier("ORM_CategoryDAO")
    @Autowired
    private CategoryDAO categoryDAO;
    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    private Category category = new Category();
    private Category anotherCategory = new Category();
    private Random random = new Random();

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
        product.setImgUrl("picpath");
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

    @Test
    public void getRandomProductWithoutCurrentCategoryIdTest(){
        List<Product> list = createListWith15Product();
        Product product = list.get(random.nextInt(15));
        long categoryId = product.getCategoryID();
        System.out.println("id " + categoryId);
        for(int i = 0; i < 15; i++){
            Product fromDAO = productDAO.getRandomProductWithoutCurrentCategoryId(categoryId);
            System.out.println("from dao id "+fromDAO.getId());
            assertTrue(categoryId != fromDAO.getCategoryID());
        }
    }

    public List<Product> createListWith15Product(){
        List<Category> list = new LinkedList<>();
        Category category;
        for(int i = 0; i < 15; i++){
            category = new Category();
            category.setName("category name" + random.nextInt(100));
            categoryDAO.create(category);
            list.add(category);
        }

        List<Product> productList = new LinkedList<>();
        Product product;
        for(int i = 0; i < 15; i++){
            product = new Product();
            product.setCategoryId(list.get(i).getId());
            product.setName("name");
            product.setPrice(123);
            product.setImgUrl("some url");
            long id = productDAO.create(product);
            productList.add(productDAO.getById(id));
        }
        return productList;
    }

}
