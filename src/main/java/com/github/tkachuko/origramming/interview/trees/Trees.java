package com.github.tkachuko.origramming.interview.trees;

import java.util.*;

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

    /**
     * Defines the lowest common ancestor of two nodes
     *
     * @param root   of tree
     * @param first  node to look ancestor for
     * @param second node to look ancestor for
     * @param <T>    type of elements in tree
     * @return LCA or null if not found
     */
    public static <T> BinaryTreeNode<T> lowestCommonAncestor(BinaryTreeNode<T> root,
                                                             BinaryTreeNode<T> first,
                                                             BinaryTreeNode<T> second) {
        return LCAHelper(root, first, second).ancestor;
    }

    private static class LCAData<T> {
        public final int numberOfTargetNodesMet;
        public final BinaryTreeNode<T> ancestor;

        public LCAData(int numberOfTargetNodesMet, BinaryTreeNode<T> ancestor) {
            this.numberOfTargetNodesMet = numberOfTargetNodesMet;
            this.ancestor = ancestor;
        }
    }

    private static <T> LCAData<T> LCAHelper(BinaryTreeNode<T> root,
                                            BinaryTreeNode<T> first,
                                            BinaryTreeNode<T> second) {
        if (root == null) return new LCAData<>(0, null);

        LCAData<T> leftData = LCAHelper(root.left, first, second);
        if (leftData.numberOfTargetNodesMet == 2) return leftData;

        LCAData<T> rightData = LCAHelper(root.right, first, second);
        if (rightData.numberOfTargetNodesMet == 2) return rightData;

        int totalTargetNodesMet = leftData.numberOfTargetNodesMet +
                rightData.numberOfTargetNodesMet +
                (root == first ? 1 : 0) + (root == second ? 1 : 0);

        return new LCAData<>(totalTargetNodesMet, totalTargetNodesMet == 2 ? root : null);
    }

    /**
     * Given tree of integers. Define ia has given path from root to any of the leafs.
     *
     * @param root      of tree
     * @param targetSum sum to be found
     * @return if tree has given sum from root to any leafs
     */
    public static boolean hasPathWithSum(BinaryTreeNode<Integer> root, int targetSum) {
        int remaining = targetSum - (root == null ? 0 : root.data);
        if (root == null) return false;
        else if (root.isLeaf()) return remaining == 0;
        else return hasPathWithSum(root.left, remaining) || hasPathWithSum(root.right, remaining);
    }

    /**
     * Collects data from the nodes of a tree to the list in in-order traversal
     *
     * @param root of tree
     * @param <T>  type of elements
     * @return all node data in in-order traversal
     */
    public static <T> List<T> inOrderTraversal(BinaryTreeNode<T> root) {
        ArrayList<T> result = new ArrayList<>();
        inOrderTraversalHelper(root, result);
        return result;
    }

    private static <T> void inOrderTraversalHelper(BinaryTreeNode<T> root, List<T> acc) {
        if (root == null) return;
        else if (root.isLeaf()) acc.add(root.data);
        else {
            inOrderTraversalHelper(root.left, acc);
            acc.add(root.data);
            inOrderTraversalHelper(root.right, acc);
        }
    }

    /**
     * Computes exterior of tree. Exterior is path from root to leftmost child, leaves, path from root to
     * rightmost child.
     *
     * @param root of the tree
     * @param <T>  type of elements
     * @return exterior of tree
     */
    public static <T> List<T> exteriorOfTree(BinaryTreeNode<T> root) {
        ArrayList<T> result = new ArrayList<>();
        rootToSideLeafPath(root, true, result);
        allLeafs(root, result);
        rootToSideLeafPath(root, false, result);
        return result;
    }

    private static <T> void allLeafs(BinaryTreeNode<T> root, List<T> acc) {
        if (root == null) return;
        else if (root.isLeaf()) acc.add(root.data);
        else {
            allLeafs(root.left, acc);
            allLeafs(root.right, acc);
        }
    }

    private static <T> void rootToSideLeafPath(BinaryTreeNode<T> root, boolean leftRoute, List<T> acc) {
        if (root != null && !root.isLeaf()) {
            acc.add(root.data);
            rootToSideLeafPath(leftRoute ? root.left : root.right, leftRoute, acc);
        }
    }

    /**
     * Computes mapping of every node to the next node to the right on this level.
     * Works for unique data and perfect trees only.
     *
     * @param root of the tree
     * @param <T>  type of elements in tree
     * @return mapping of every node to the next node to the right on this level
     */
    public static <T> Map<BinaryTreeNode<T>, BinaryTreeNode<T>> leftTheRightLevelMapping(BinaryTreeNode<T> root) {
        HashMap<BinaryTreeNode<T>, BinaryTreeNode<T>> result = new HashMap<>();
        while (root != null && root.hasLeft()) {
            leftToRightLevelMapping(root, result);
            root = root.left;
        }
        return result;
    }

    private static <T> void leftToRightLevelMapping(BinaryTreeNode<T> leftmostOfTheLevel,
                                                   Map<BinaryTreeNode<T>, BinaryTreeNode<T>> acc) {
        while (leftmostOfTheLevel != null) {
            acc.put(leftmostOfTheLevel.left, leftmostOfTheLevel.right);

            if (acc.containsKey(leftmostOfTheLevel)) {
                acc.put(leftmostOfTheLevel.right, acc.get(leftmostOfTheLevel).left);
            }

            leftmostOfTheLevel = acc.get(leftmostOfTheLevel);
        }
    }
}