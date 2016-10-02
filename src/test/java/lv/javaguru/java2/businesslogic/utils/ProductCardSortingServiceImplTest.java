package lv.javaguru.java2.businesslogic.utils;

import lv.javaguru.java2.dto.ProductCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ProductCardSortingServiceImplTest {

    private ProductCardSortingServiceImpl sortingService;

    @Mock
    private ProductCard card1;
    @Mock
    private ProductCard card2;
    @Mock
    private ProductCard card3;
    @Mock
    private ProductCard card4;

    private List<ProductCard> list = new ArrayList<>();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        sortingService = new ProductCardSortingServiceImpl();
        list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        list.add(card3);
        list.add(card4);
    }

    @Test
    public void testSortByName() {
        Mockito.doReturn("C").when(card1).getProductName();
        Mockito.doReturn("A").when(card2).getProductName();
        Mockito.doReturn("D").when(card3).getProductName();
        Mockito.doReturn("B").when(card4).getProductName();

        sortingService.sort("BYNAME", list);

        assertEquals(card2, list.get(0));
        assertEquals(card4, list.get(1));
        assertEquals(card1, list.get(2));
        assertEquals(card3, list.get(3));
    }

    @Test
    public void testSortByNameReversed() {
        Mockito.doReturn("C").when(card1).getProductName();
        Mockito.doReturn("A").when(card2).getProductName();
        Mockito.doReturn("D").when(card3).getProductName();
        Mockito.doReturn("B").when(card4).getProductName();

        sortingService.sortReversed("BYNAME", list);

        assertEquals(card3, list.get(0));
        assertEquals(card1, list.get(1));
        assertEquals(card4, list.get(2));
        assertEquals(card2, list.get(3));
    }

    @Test
    public void testByDefaultWhenWrongSortKeyProvided() {
        Mockito.doReturn("C").when(card1).getProductName();
        Mockito.doReturn("A").when(card2).getProductName();
        Mockito.doReturn("D").when(card3).getProductName();
        Mockito.doReturn("B").when(card4).getProductName();

        sortingService.sort("WRONGKEY", list);

        assertEquals(card2, list.get(0));
        assertEquals(card4, list.get(1));
        assertEquals(card1, list.get(2));
        assertEquals(card3, list.get(3));
    }

    @Test
    public void sortByQuantity() {
        Mockito.doReturn(0l).when(card1).getQuantity();
        Mockito.doReturn(2l).when(card2).getQuantity();
        Mockito.doReturn(6l).when(card3).getQuantity();
        Mockito.doReturn(5l).when(card4).getQuantity();

        sortingService.sort("BYSTOCK", list);

        assertEquals(card3, list.get(0));
        assertEquals(card4, list.get(1));
        assertEquals(card2, list.get(2));
        assertEquals(card1, list.get(3));
    }


}