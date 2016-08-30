package lv.javaguru.java2.servlet;

import com.sun.media.sound.AudioFloatFormatConverter;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class ReverseRouterTest {
    private static final String DEFAULT_PATH = "/defaultPath";
    private static ReverseRouter reverseRouter;

    @BeforeClass
    public static void beforeClass() {
        Map<String, Object> controllers = new HashMap<String, Object>();
        controllers.put("/IntegerLink", new Integer(123));
        controllers.put("/audioSomething", new AudioFloatFormatConverter());
        controllers.put("/error", new IllegalFormatCodePointException(123));
        controllers.put("/error", new IllegalFormatCodePointException(123));
        reverseRouter = new ReverseRouter(controllers, DEFAULT_PATH);
        reverseRouter.setAlias(Product.class, Integer.class);
    }

    @Test
    public void testExistingPath() {
        String path = reverseRouter.linkTo("Integer");
        assertEquals("/IntegerLink", path);

        String anotherPath = reverseRouter.linkTo("IllegalFormatCodePointException");
        assertEquals("/error", anotherPath);
    }

    @Test
    public void testExistingPathWithId() {
        String path = reverseRouter.linkTo("Integer", "124");
        assertEquals("/IntegerLink?id=124", path);

        String anotherPath = reverseRouter.linkTo("IllegalFormatCodePointException", "100500");
        assertEquals("/error?id=100500", anotherPath);
    }

    @Test
    public void testBadPath() {
        String path = reverseRouter.linkTo("XMLStreamWriterFactory.Zephyr");
        assertEquals(DEFAULT_PATH, path);
    }

    @Test
    public void testBadPathWithId() {
        String path = reverseRouter.linkTo("Cookie", "9001");
        assertEquals(DEFAULT_PATH, path);
    }

    @Test
    public void testExistingPathByClassName() {
        String path = reverseRouter.linkTo(Integer.class);
        assertEquals("/IntegerLink", path);

        String anotherPath = reverseRouter.linkTo(IllegalFormatCodePointException.class);
        assertEquals("/error", anotherPath);
    }

    @Test
    public void testExistingPathByClassNameWithId() {
        String path = reverseRouter.linkTo(Integer.class, 124L);
        assertEquals("/IntegerLink?id=124", path);

        String anotherPath = reverseRouter.linkTo(IllegalFormatCodePointException.class, 100500L);
        assertEquals("/error?id=100500", anotherPath);
    }

    @Test
    public void testAliasesRoutesToOriginal() {
        String path = reverseRouter.linkTo(Product.class);
        assertEquals("/IntegerLink", path);
    }

    @Test
    public void testAliasesWithIdRoutesToOriginalWithId() {
        String path = reverseRouter.linkTo(Product.class, 124L);
        assertEquals("/IntegerLink?id=124", path);
    }

    @Test
    public void testRouteAliasedDomainObject() {
        Product product = new Product();
        product.setId(123);
        String path = reverseRouter.linkTo(product);
        assertEquals("/IntegerLink?id=123", path);
    }

    @Test
    public void testRouteUnaliasedDomainObject() {
        User user = new User();
        user.setId(1234);
        String path = reverseRouter.linkTo(user);
        assertEquals(DEFAULT_PATH, path);
    }

}