package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.helpers.CategoryTree;
import lv.javaguru.java2.helpers.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryEditorServiceImpl implements CategoryEditorService{

    @Autowired
    @Qualifier("ORM_CategoryDAO")
    private CategoryDAO categoryDAO;
    @Autowired
    private TemplateService templateService;
    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CategoryTree categoryTree;

    @Override
    public Map<String, Object> getCategoryList() {
        Map<String, Object> map = new HashMap<>();
        List<Category> categories = categoryDAO.getAll();
        map.put("categories", categories);
        map.putAll(templateService.model());
        return map;
    }

    @Override
    public void addChild(long fatherId) {
        Category father = categoryDAO.getById(fatherId);
        Category son = new Category();
        son.setFatherId(father.getId());
        son.setName(father.getName() + "'s child");
        categoryDAO.create(son);
        categoryTree.refresh(categoryDAO.getAll());
    }

    @Override
    public void delete(long categoryId){
        Category category = categoryDAO.getById(categoryId);
        List<Product> productList = productDAO.getAllByCategory(category);
        TreeNode<Category> categoryTreeNode = categoryTree.getNode(category);

        for (Category child : categoryTreeNode.getChildren()) {
            child.setFatherId(category.getFatherId());
            child.setName("[" + category.getName() + "]" + child.getName());
            categoryDAO.update(child);
        }
        for(Product product : productList){
            product.setCategoryId(category.getFatherId());
            productDAO.update(product);
        }
        categoryDAO.delete(category);
        categoryTree.refresh(categoryDAO.getAll());
    }

    @Override
    public void edit(Category category){
        Category oldCategory = categoryDAO.getById(category.getId());
        oldCategory.setName(category.getName());
        //oldCategory.setFatherId(category.getFatherId());
        categoryDAO.update(oldCategory);
        categoryTree.refresh(categoryDAO.getAll());
    }
}
