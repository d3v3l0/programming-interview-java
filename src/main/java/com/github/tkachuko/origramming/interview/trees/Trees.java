package com.github.tkachuko.origramming.interview.trees;

import java.util.Objects;

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

    /**
     * Checks if tree is symmetric
     *
     * @param root of binary tree
     * @param <T>  type of elements in tree
     * @return true if try is symmetric, false - otherwise
     */
    public static <T> boolean isSymmetric(BinaryTreeNode<T> root) {
        return isMirrored(root.left, root.right);
    }

    private static <T> boolean isMirrored(BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        if (left == null && right == null) return true;
        else if (left != null && right != null)
            return Objects.equals(left.data, right.data) &&
                    isMirrored(left.left, right.right) &&
                    isMirrored(left.right, right.left);
        else return false;
    }
}