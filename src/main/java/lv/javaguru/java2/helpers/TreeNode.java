package lv.javaguru.java2.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class TreeNode<Type> {
    private final Type instance;
    private TreeNode<Type> parent = null;
    private List<TreeNode<Type>> children = null;
    private List<TreeNode<Type>> ancestors = null;

    public TreeNode(Type self) {
        this.ancestors = new ArrayList<TreeNode<Type>>();
        this.instance = self;
    }

    public void addAncestors(List<TreeNode<Type>> ancestors) {
        this.ancestors.addAll(ancestors);
        if (this.parent != null)
            this.parent.addAncestors(ancestors);
    }

    public Type getParent() {
        return this.parent == null ? null : this.parent.getInstance();
    }

    public void setParent(TreeNode<Type> parent) {
        this.parent = parent;
    }

    public TreeNode<Type> getParentNode() {
        return this.parent;
    }

    public Type getInstance() {
        return this.instance;
    }

    private List<Type> extractInstances(List<TreeNode<Type>> nodes) {
        List<Type> list = nodes.stream().map(node -> node.getInstance()).collect(Collectors.toList());
        return list;
    }

    public List<Type> getChildren() {
        return extractInstances(this.children);
    }

    public void setChildren(List<TreeNode<Type>> children) {
        this.children = children;
        addAncestors(children);
    }

    public List<TreeNode<Type>> getChildrenNodes() {
        return this.children;
    }

    public List<Type> getAncestors() {
        return extractInstances(this.ancestors);
    }

    public String toString() {
        return "[node-" + this.instance + "]";
    }
}
