package lv.javaguru.java2.helpers;

import lv.javaguru.java2.domain.Category;

import java.util.*;
import java.util.stream.Collectors;

public class CategoryTree implements Iterable<Category> {

    Map<Long, TreeNode<Category>> nodesById = new HashMap<Long, TreeNode<Category>>();
    Map<Long, List<TreeNode<Category>>> nodesByParentId = new HashMap<>();
    List<Category> asCategoryList;

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
        registerNodeParents();
        registerNodeChildren();
        buildOrderedList();
    }

    public List<Category> getChildren(Category category) {
        if (category == null)
            return null;
        TreeNode<Category> node = nodesById.get(category.getId());
        return node == null ? null : node.getChildren();
    }

    public List<Category> getDescendants(Category category) {
        if (category == null)
            return null;
        TreeNode<Category> node = nodesById.get(category.getId());
        return node == null ? null : node.getDescendants();
    }

    public Category getParent(Category category) {
        if (category == null)
            return null;
        TreeNode<Category> node = nodesById.get(category.getId());
        return node == null ? null : node.getParent();
    }

    public Category getRoot() {
        return nodesByParentId.get(0L).get(0).getInstance();
    }

    public TreeNode<Category> getNode(Category category) {
        return nodesById.get(category.getId());
    }

    public List<Category> asOrderedList() {
        return asCategoryList;
    }

    @Override
    public Iterator<Category> iterator() {
        return asCategoryList.iterator();
    }


    private void registerNodeChildren() {
        for (Map.Entry<Long, TreeNode<Category>> entry : nodesById.entrySet()) {
            TreeNode<Category> node = entry.getValue();
            Category instance = node.getInstance();
            List<TreeNode<Category>> childs = nodesByParentId.getOrDefault(instance.getId(), new ArrayList<>());
            node.setChildren(childs);
        }
    }

    private void registerNodeParents() {
        for (Map.Entry<Long, TreeNode<Category>> entry : nodesById.entrySet()) {
            TreeNode<Category> node = entry.getValue();
            Category instance = node.getInstance();
            TreeNode<Category> parent = nodesById.get(instance.getFather_id());
            node.setParent(parent);
        }
    }

    private void buildOrderedList() {
        if (nodesById.size() == 0) {
            asCategoryList = new ArrayList<Category>();
            return;
        }

        List<TreeNode<Category>> asList = new ArrayList<>();
        Category root = this.getRoot();
        asList.add(this.getNode(root));
        list:
        while (true) {
            for (int i = 0; i < asList.size(); i++) {
                List<TreeNode<Category>> node = asList.get(i).getChildrenNodes();
                if (node.size() == 0)
                    continue;
                if (!asList.contains(node.get(0))) {
                    asList.addAll(i + 1, asList.get(i).getChildrenNodes());
                    continue list;
                }
            }
            break;
        }
        asCategoryList = asList.stream().map(node -> node.getInstance()).collect(Collectors.toList());
    }
}



