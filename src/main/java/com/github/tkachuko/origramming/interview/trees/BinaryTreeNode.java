package com.github.tkachuko.origramming.interview.trees;

public class BinaryTreeNode<T> {

    public final T data;
    public final BinaryTreeNode<T> left, right;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(T data) {
        this(data, null, null);
    }

    public static <K> BinaryTreeNode<K> node(K data, K left, K right) {
        BinaryTreeNode<K> leftNode = new BinaryTreeNode<>(left);
        BinaryTreeNode<K> rightNode = new BinaryTreeNode<>(right);
        return new BinaryTreeNode<>(data, leftNode, rightNode);
    }

    public static <K> BinaryTreeNode<K> node(K data) {
        return new BinaryTreeNode<>(data);
    }

    public static <K> BinaryTreeNode<K> tree(K data, BinaryTreeNode<K> left, BinaryTreeNode<K> right) {
        return new BinaryTreeNode<>(data, left, right);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public int height() {
        int rightHeight = hasRight() ? right.height() : 0;
        int leftHeight = hasLeft() ? left.height() : 0;
        return 1 + Math.max(rightHeight, leftHeight);
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "data=" + data +
                '}';
    }
}
