package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpagehelpers.CategoryChooseController;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PathResolverTest {

    private static String DEFAULT_PATH = "error.jsp";

    private static PathResolver pathResolver;

    @BeforeClass
    public static void beforeClass() {
        pathResolver = new PathResolver();
        pathResolver.registerPath("/logoutLinkHere", new LogoutController());
        pathResolver.registerPath("/someProductLink", new ProductController());
        pathResolver.registerPath("/moreLinksHere", new FrontPageController());
        pathResolver.registerPath("/anyOtherLink", new CategoryChooseController());
        pathResolver.setAlias(Product.class, ProductController.class);
    }

    @Test
    public void testExistingPath() {
        String path = pathResolver.linkTo("LogoutController");
        assertEquals("/logoutLinkHere", path);

        String anotherPath = pathResolver.linkTo("ProductController");
        assertEquals("/someProductLink", anotherPath);
    }

    @Test
    public void testExistingPathWithId() {
        String path = pathResolver.linkTo("LogoutController", "124");
        assertEquals("/logoutLinkHere?id=124", path);

        String anotherPath = pathResolver.linkTo("FrontPageController", "100500");
        assertEquals("/moreLinksHere?id=100500", anotherPath);
    }

    @Test
    public void testBadPath() {
        String path = pathResolver.linkTo("WrongController");
        assertEquals(DEFAULT_PATH, path);
    }

    @Test
    public void testBadPathWithId() {
        String path = pathResolver.linkTo("WrongController", "9001");
        assertEquals(DEFAULT_PATH, path);
    }

    @Test
    public void testExistingPathByClassName() {
        String path = pathResolver.linkTo(LogoutController.class);
        assertEquals("/logoutLinkHere", path);

        String anotherPath = pathResolver.linkTo(CategoryChooseController.class);
        assertEquals("/anyOtherLink", anotherPath);
    }

    @Test
    public void testExistingPathByClassNameWithId() {
        String path = pathResolver.linkTo(LogoutController.class, 124L);
        assertEquals("/logoutLinkHere?id=124", path);

        String anotherPath = pathResolver.linkTo(CategoryChooseController.class, 100500L);
        assertEquals("/anyOtherLink?id=100500", anotherPath);
    }

    @Test
    public void testAliasesRoutesToOriginal() {
        String path = pathResolver.linkTo(Product.class);
        assertEquals("/someProductLink", path);
    }

    @Test
    public void testAliasesWithIdRoutesToOriginalWithId() {
        String path = pathResolver.linkTo(Product.class, 124L);
        assertEquals("/someProductLink?id=124", path);
    }

    @Test
    public void testRouteAliasedDomainObject() {
        Product product = new Product();
        product.setId(123);
        String path = pathResolver.linkTo(product);
        assertEquals("/someProductLink?id=123", path);
    }

    @Test
    public void testRouteUnaliasedDomainObject() {
        User user = new User();
        user.setId(1234);
        String path = pathResolver.linkTo(user);
        assertEquals(DEFAULT_PATH, path);
    }

}