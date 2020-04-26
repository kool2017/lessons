package com.kool.lessons;

/**
 * @author luyu
 */
public class MyTree {
    Node root;

    static class Node {
        int hash;
        String value;
        Node lt;
        Node rt;
        Node prev;
    }

    /**
     * 如果根节点为空，输入为根节点，如果不为空，将节点挂到根下
     *
     * @param value
     */
    public void put(String value) {
        if (value == null) {
            throw new RuntimeException("value不能为空");
        }
        if (root == null) {
            root = newNode(null, value);
        } else {
            addNode(root, value);
        }
    }

    /**
     * 将节点挂到树下
     * 比较树节点与新节点的大小，如果新节点小于树节点，则挂到左子树，如果新节点大于树节点，则挂到右树，如果新节点等于树节点，则覆盖树节点。
     *
     * @param subTree
     * @param value
     */
    private void addNode(Node subTree, String value) {
        if (value.compareToIgnoreCase(subTree.value) < 0) {
            Node lt = subTree.lt;
            if (lt == null) {
                subTree.lt = newNode(subTree, value);
            } else {
                addNode(lt, value);
            }
        } else if (value.compareToIgnoreCase(subTree.value) > 0) {
            Node rt = subTree.rt;
            if (rt == null) {
                subTree.rt = newNode(subTree, value);
            } else {
                addNode(rt, value);
            }
        } else if (value.compareToIgnoreCase(subTree.value) <= 0) {
            subTree.value = value;
        }
    }

    private Node newNode(Node prev, String value) {
        Node n = new Node();
        n.hash = value.hashCode();
        n.value = value;
        n.prev = prev;
        return n;
    }

    public void printTree(){
        if (root == null) {
            print("null");
        }else {
            printSubTree(root);
        }
    }

    private void printSubTree(Node subTree) {
        if (subTree != null) {

            printSubTree(subTree.lt);
            print(" ");
            print(subTree.value);
            print(" ");
            printSubTree(subTree.rt);
        }
    }

    private void print(String str){
        System.out.print(str);
    }

    public static void main(String[] args) {
        MyTree myTree = new MyTree();
        for (int i = 0; i < 100; i++) {
            myTree.put(String.valueOf(i));
        }
        myTree.put("a");
        myTree.printTree();
    }
}
