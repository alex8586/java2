package lv.javaguru.java2.helpers;

import lv.javaguru.java2.domain.Category;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryTreeTest {

    private static Category badCategory;
    private static Category rootCategory;
    private static Category rootsChild1;
    private static Category rootsChild2;
    private List<Category> emptyList = new ArrayList<Category>();
    private CategoryTree emptyCategoryTree = new CategoryTree(emptyList);
    private List<Category> listForTests;

    @BeforeClass
    public static void beforeClass() {
        badCategory = new Category();
        badCategory.setId(-123);
        rootCategory = new Category();
        rootCategory.setId(7);
        rootCategory.setFather_id(0);
        rootCategory.setName("root");
        rootsChild1 = nthChildFor(rootCategory, 1);
        rootsChild2 = nthChildFor(rootCategory, 2);
    }

    private static Category nthChildFor(Category category, int ith) {
        Category child = new Category();
        child.setFather_id(category.getId());
        child.setId(category.getId() * 10 + ith);
        child.setName("son " + " of " + category.getId());
        return child;
    }

    @Before
    public void before() {
        listForTests = new ArrayList<Category>();
    }

    @Test
    public void testParentReturnNullWhenWrongCategorySupplied() {
        assertEquals(null, emptyCategoryTree.getParent(badCategory));
        assertEquals(null, emptyCategoryTree.getParent(null));
    }

    @Test
    public void testChildrenReturnNullWhenWrongCategorySupplied() {
        assertEquals(null, emptyCategoryTree.getChildren(badCategory));
        assertEquals(null, emptyCategoryTree.getChildren(null));
    }

    @Test
    public void testAncestorsReturnNullWhenWrongCategorySupplied() {
        assertEquals(null, emptyCategoryTree.getAncestors(badCategory));
        assertEquals(null, emptyCategoryTree.getAncestors(null));
    }

    @Test
    public void testParentAndChild() {
        listForTests.add(rootCategory);
        listForTests.add(rootsChild1);
        CategoryTree categoryTree = new CategoryTree(listForTests);

        assertEquals(rootCategory, categoryTree.getParent(rootsChild1));
        assertEquals(rootsChild1, categoryTree.getChildren(rootCategory).get(0));

        List<Category> childs = categoryTree.getChildren(rootCategory);
        assertTrue(childs.contains(rootsChild1));
    }

    @Test
    public void testParentAndTwoChildren() {
        listForTests.add(rootCategory);
        listForTests.add(rootsChild1);
        listForTests.add(rootsChild2);
        CategoryTree categoryTree = new CategoryTree(listForTests);

        assertEquals(rootCategory, categoryTree.getParent(rootsChild1));
        assertEquals(rootCategory, categoryTree.getParent(rootsChild2));

        List<Category> childs = categoryTree.getChildren(rootCategory);
        assertTrue(childs.contains(rootsChild1));
        assertTrue(childs.contains(rootsChild2));

        Iterator<Category> iterator = categoryTree.iterator();
        assertEquals(rootCategory, iterator.next());
        assertEquals(rootsChild1, iterator.next());
        assertEquals(rootsChild2, iterator.next());
        assertFalse(iterator.hasNext());

    }

    @Test
    public void testParentChildsAndUnlinkedNode() {
        listForTests.add(rootCategory);
        listForTests.add(rootsChild1);
        listForTests.add(rootsChild2);
        listForTests.add(badCategory);
        CategoryTree categoryTree = new CategoryTree(listForTests);

        assertEquals(rootCategory, categoryTree.getParent(rootsChild1));
        assertEquals(rootCategory, categoryTree.getParent(rootsChild2));
        assertEquals(null, categoryTree.getParent(rootCategory));
        assertEquals(null, categoryTree.getParent(badCategory));

        List<Category> childs = categoryTree.getChildren(rootCategory);
        assertEquals(2, childs.size());
        assertTrue(childs.contains(rootsChild1));
        assertTrue(childs.contains(rootsChild2));

        childs = categoryTree.getChildren(badCategory);
        assertEquals(0, childs.size());
    }

    @Test
    public void testEightNodeTree() {
        listForTests.add(rootCategory);
        listForTests.add(rootsChild1);
        listForTests.add(rootsChild2);
        Category son1ofRootsChild1 = nthChildFor(rootsChild1, 1);
        listForTests.add(son1ofRootsChild1);
        listForTests.add(nthChildFor(rootsChild1, 2));
        listForTests.add(nthChildFor(rootsChild2, 1));

        listForTests.add(nthChildFor(son1ofRootsChild1, 1));
        Category son2ofson1ofRootsChild1 = nthChildFor(son1ofRootsChild1, 2);
        listForTests.add(son2ofson1ofRootsChild1);
        listForTests.add(nthChildFor(son2ofson1ofRootsChild1, 1));

        CategoryTree categoryTree = new CategoryTree(listForTests);

        assertEquals(711, categoryTree.getParent(son2ofson1ofRootsChild1).getId());
        assertEquals(1, categoryTree.getChildren(son2ofson1ofRootsChild1).size());
        assertEquals(1, categoryTree.getAncestors(son2ofson1ofRootsChild1).size());
        assertEquals(71121, categoryTree.getChildren(son2ofson1ofRootsChild1).get(0).getId());
        assertEquals(71121, categoryTree.getAncestors(son2ofson1ofRootsChild1).get(0).getId());

        assertEquals(71, categoryTree.getParent(son1ofRootsChild1).getId());
        assertEquals(2, categoryTree.getChildren(son1ofRootsChild1).size());
        assertEquals(3, categoryTree.getAncestors(son1ofRootsChild1).size());

        assertEquals(2, categoryTree.getChildren(rootCategory).size());
        assertEquals(8, categoryTree.getAncestors(rootCategory).size());

        assertEquals(1, categoryTree.getChildren(rootsChild2).size());
        assertEquals(1, categoryTree.getAncestors(rootsChild2).size());

        assertEquals(2, categoryTree.getChildren(rootsChild1).size());
        assertEquals(5, categoryTree.getAncestors(rootsChild1).size());

        Iterator<Category> iterator = categoryTree.iterator();
        assertEquals(rootCategory, iterator.next());
        assertEquals(rootsChild1, iterator.next());
        assertEquals(son1ofRootsChild1, iterator.next());
        iterator.next();
        assertEquals(son2ofson1ofRootsChild1, iterator.next());
        iterator.next();
        iterator.next();
        assertEquals(rootsChild2, iterator.next());
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}