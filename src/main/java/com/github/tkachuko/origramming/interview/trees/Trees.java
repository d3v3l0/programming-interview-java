package com.github.tkachuko.origramming.interview.trees;

public class Trees {

    /**
     * Checks if binary tree is balanced or if height of the right and left subtree is different not more than one.
     *
     * @param root of the tree
     * @param <T>  type of elements in a tree
     * @return if tree is balanced or not
     */
    public static <T> boolean isBalanced(BinaryTreeNode<T> root) {
        if (root == null) return true;
        int rightHeight = root.hasRight() ? root.right.height() : 0;
        int leftHeight = root.hasLeft() ? root.left.height() : 0;
        return Math.abs(rightHeight - leftHeight) <= 1;
    }
}
