package com.parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static final String ROOT = "root";
    private static final String COMMA = ",";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String HYPHEN = "-";

    private static List<Tree> createTreeFromString(String data) {
        String[] strArr = data.split("");
        Tree root = new Tree(null, ROOT, new ArrayList<>());
        Tree node = root;
        for (String str : strArr) {
            switch (str) {
                case LEFT_PARENTHESIS:
                    node = new Tree(node);
                    node.getParent().getNodes().add(node);
                    break;
                case RIGHT_PARENTHESIS:
                    Tree parent = node.getParent() == null ? null : node.getParent().getParent();
                    node = new Tree(parent);
                    if (node.getParent() != null) {
                        node.getParent().getNodes().add(node);
                    }
                    break;
                case COMMA:
                    if (!node.getValue().isEmpty()) {
                        node = new Tree(node.getParent());
                        node.getParent().getNodes().add(node);
                    }
                    break;
                default:
                    node.setValue(node.getValue() + str);
                    break;
            }
        }

        return root.getNodes();
    }

    private static void printTree(List<Tree> trees, String lvl, boolean isSortOrder) {
        if (isSortOrder) {
            trees.sort(Comparator.comparing(Tree::getValue));
        }
        for (Tree node : trees) {
            if (!node.getValue().isEmpty()) {
                System.out.println(lvl + node.getValue());
            }
            if (!node.getNodes().isEmpty()) {
                printTree(node.getNodes(), lvl + HYPHEN, isSortOrder);
            }
        }
    }

    public static void main(String[] args) {
        String strToParse = "(id,created,employee(id,firstname,employeeType(id), lastname),location)";
        strToParse = strToParse.replaceAll(" ", "");

        List<Tree> tree = createTreeFromString(strToParse);
        System.out.println("String to parse = " + strToParse);
        System.out.println("-----------------------Regular Output-----------------------");
        printTree(tree, "", false);
        System.out.println("-----------------------Sorted Output-----------------------");
        printTree(tree, "", true);
    }
}
