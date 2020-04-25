package com.parser;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private Tree parent;
    private List<Tree> nodes = new ArrayList<>();
    private String value = "";

    public Tree(Tree parent) {
        this.parent = parent;
    }

    public Tree(Tree parent, String value, List<Tree> nodes) {
        this.parent = parent;
        this.value = value;
        this.nodes = nodes;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public String getValue() {
        return value.trim();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Tree> getNodes() {
        return nodes;
    }

    public void setNodes(List<Tree> nodes) {
        this.nodes = nodes;
    }
}
