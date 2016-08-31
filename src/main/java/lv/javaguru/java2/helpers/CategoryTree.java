package lv.javaguru.java2.helpers;

import lv.javaguru.java2.domain.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryTree {

    Map<Long, TreeNode<Category>> nodesById = new HashMap<Long, TreeNode<Category>>();
    Map<Long, List<TreeNode<Category>>> nodesByParentId = new HashMap<>();

    public CategoryTree(List<Category> categories) {
        for (Category category : categories) {
            TreeNode<Category> node = new TreeNode<Category>(category);
            nodesById.put(category.getId(), node);
            if (nodesByParentId.containsKey(category.getFather_id())) {
                nodesByParentId.get(category.getFather_id()).add(node);
            } else {
                List<TreeNode<Category>> children = new ArrayList<TreeNode<Category>>();
                children.add(node);
                nodesByParentId.put(category.getFather_id(), children);
            }
        }
        for (Map.Entry<Long, TreeNode<Category>> entry : nodesById.entrySet()) {
            TreeNode<Category> node = entry.getValue();
            Category instance = node.getInstance();
            TreeNode<Category> parent = nodesById.get(instance.getFather_id());
            node.setParent(parent);
        }

        for (Map.Entry<Long, TreeNode<Category>> entry : nodesById.entrySet()) {
            TreeNode<Category> node = entry.getValue();
            Category instance = node.getInstance();
            List<TreeNode<Category>> childs = nodesByParentId.getOrDefault(instance.getId(), new ArrayList<>());
            node.setChildren(childs);
        }
    }

    public List<Category> getChildren(Category category) {
        if (category == null)
            return null;
        TreeNode<Category> node = nodesById.get(category.getId());
        return node == null ? null : node.getChildren();
    }

    public List<Category> getAncestors(Category category) {
        if (category == null)
            return null;
        TreeNode<Category> node = nodesById.get(category.getId());
        return node == null ? null : node.getAncestors();
    }

    public Category getParent(Category category) {
        if (category == null)
            return null;
        TreeNode<Category> node = nodesById.get(category.getId());
        return node == null ? null : node.getParent();
    }
}

