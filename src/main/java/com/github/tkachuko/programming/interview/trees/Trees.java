package com.github.tkachuko.programming.interview.trees;

import java.util.*;

public class Trees {

    /**
     * Check if binary tree is binary search tree
     *
     * @param root of tree
     * @return if satisfies BST property
     */
    public static boolean isBST(BinaryTreeNode<Integer> root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBST(BinaryTreeNode<Integer> root, int min, int max) {
        if (root == null) return true;

        boolean rootIsValid = root.data > min && root.data <= max;

        return rootIsValid
                && isBST(root.left, min, root.data)
                && isBST(root.right, root.data, max);
    }

    /**
     * Checks if binary tree is balanced or if height of the right
     * and left subtree is different not more than one
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
        return root == null || isMirrored(root.left, root.right);
    }

    private static <T> boolean isMirrored(BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        if (left != null && right != null) {
            return Objects.equals(left.data, right.data)
                    && isMirrored(left.left, right.right)
                    && isMirrored(left.right, right.left);
        } else return left == null && right == null;
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
        final int numberOfTargetNodesMet;
        final BinaryTreeNode<T> ancestor;

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

        return new LCAData<>(totalTargetNodesMet, root);
    }

    /**
     * Given tree of integers. Define if it has given path from root to any of the leafs.
     *
     * @param root      of tree
     * @param targetSum sum to be found
     * @return if tree has given sum from root to any leafs
     */
    public static boolean hasRootPathWithSum(BinaryTreeNode<Integer> root, int targetSum) {
        if (root == null) return false;
        int remaining = targetSum - root.data;
        if (root.isLeaf()) return remaining == 0;
        return hasRootPathWithSum(root.left, remaining) ||
                hasRootPathWithSum(root.right, remaining);
    }

    /**
     * Find all paths in a tree that have given sum. Path may not start from the tree root.
     *
     * @param root   of the tree
     * @param target sum to be found
     * @return all paths in a tree that have given sum
     */
    public static Set<List<Integer>> hasAnyPathWithSum(BinaryTreeNode<Integer> root, int target) {
        Set<List<Integer>> result = new HashSet<>();
        hasAnyPathWithSumHelper(root, target, new LinkedList<>(), result);
        return result;
    }

    public static void hasAnyPathWithSumHelper(BinaryTreeNode<Integer> root,
                                               int target,
                                               LinkedList<Integer> path,
                                               Set<List<Integer>> acc) {
        if (root == null) return;

        path.add(root.data);

        hasAnyPathWithSumHelper(root.left, target, path, acc);
        hasAnyPathWithSumHelper(root.right, target, path, acc);

        int sum = 0;

        for (int i = path.size() - 1; i > 0; i--) {
            sum += path.get(i);
            if (sum == target) {
                List<Integer> pathWithSum = path.subList(i, path.size());
                acc.add(new ArrayList<>(pathWithSum));
            }
        }

        for (int i = 0; i < path.size(); i++) {
            sum += path.get(i);
            if (sum == target) {
                List<Integer> pathWithSum = path.subList(i, path.size());
                acc.add(new ArrayList<>(pathWithSum));
            }
        }

        path.removeLast();
    }

    /**
     * Collects data from the nodes of a tree to the list in in-order traversal
     *
     * @param root of tree
     * @param <T>  type of elements
     * @return all node data in in-order traversal
     */
    public static <T> List<T> inOrderTraversal(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        inOrderTraversalHelper(root, result);
        return result;
    }

    private static <T> void inOrderTraversalHelper(BinaryTreeNode<T> root, List<T> acc) {
        if (root != null) {
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
        List<T> result = new ArrayList<>();
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
        if (root == null) return Collections.emptyMap();
        Map<BinaryTreeNode<T>, BinaryTreeNode<T>> result = new HashMap<>();
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

    /**
     * Finds greatest level sum in tree
     *
     * @param root of tree
     * @return max sum of the level
     */
    public static int maxLevelSum(BinaryTreeNode<Integer> root) {
        List<BinaryTreeNode<Integer>> level = new ArrayList<>();
        level.add(root);
        int maxSum = Integer.MIN_VALUE;

        while (!level.isEmpty()) {
            int sum = level.stream().mapToInt(node -> node.data).sum();
            if (sum > maxSum) maxSum = sum;

            List<BinaryTreeNode<Integer>> newLevel = new ArrayList<>();
            for (BinaryTreeNode<Integer> node : level) {
                if (node.left != null) newLevel.add(node.left);
                if (node.right != null) newLevel.add(node.right);
            }

            level = newLevel;
        }

        return maxSum;
    }

    /**
     * Finds all keys in range of BST
     *
     * @param root of BST
     * @return keys in range [min, max]
     */
    public static Set<Integer> allKeysInRange(BinaryTreeNode<Integer> root, int min, int max) {
        Set<Integer> result = new HashSet<>();
        allKeysInRange(root, min, max, result);
        return result;
    }

    private static void allKeysInRange(BinaryTreeNode<Integer> root,
                                       int min, int max, Set<Integer> acc) {
        if (root == null) return;

        int key = root.data;
        if (min <= key && key <= max) {
            acc.add(key);
            allKeysInRange(root.left, min, max, acc);
            allKeysInRange(root.right, min, max, acc);
        } else if (key < min) allKeysInRange(root.right, min, max, acc);
        else allKeysInRange(root.left, min, max, acc);
    }
}