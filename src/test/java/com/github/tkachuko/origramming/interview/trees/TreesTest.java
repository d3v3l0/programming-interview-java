package com.github.tkachuko.origramming.interview.trees;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.tkachuko.origramming.interview.trees.BinaryTreeNode.node;
import static com.github.tkachuko.origramming.interview.trees.BinaryTreeNode.tree;
import static com.github.tkachuko.origramming.interview.trees.Trees.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.*;

public class TreesTest {

    @Test
    public void shouldCheckTreeIsBalanced() {
        BinaryTreeNode<Integer> tree =
                tree(1,
                        node(
                                2,
                                3,
                                4
                        ),
                        node(
                                5,
                                6,
                                7
                        )
                );
        assertTrue(isBalanced(tree));
    }

    @Test
    public void shouldCheckTreeIsNotBalanced() {
        BinaryTreeNode<Integer> tree =
                tree(1,
                        tree(
                                2,
                                node(
                                        3,
                                        4,
                                        5
                                ),
                                node(
                                        11
                                )
                        ),
                        node(9)
                );
        assertFalse(isBalanced(tree));
    }

    @Test
    public void shouldCheckTreeIsNotSymmetric() {
        BinaryTreeNode<Integer> tree = tree(1,
                tree(
                        2,
                        node(
                                3,
                                4,
                                5
                        ),
                        node(
                                11
                        )
                ),
                node(9)
        );
        assertFalse(isSymmetric(tree));
    }

    @Test
    public void shouldCheckTreeIsSymmetric() {
        BinaryTreeNode<Integer> tree =
                tree(1,
                        node(
                                2,
                                3,
                                4
                        ),
                        node(
                                2,
                                4,
                                3
                        )
                );
        assertTrue(isSymmetric(tree));
    }

    @Test
    public void shouldReturnRootAsLCA() {
        BinaryTreeNode<Integer> left = node(2, 3, 4);
        BinaryTreeNode<Integer> right = node(2, 4, 3);
        BinaryTreeNode<Integer> root = tree(1, left, right);
        assertEquals(root, lowestCommonAncestor(root, left, right));
    }

    @Test
    public void shouldReturnFirstChildAsLCA() {
        BinaryTreeNode<Integer> first = node(15);
        BinaryTreeNode<Integer> second = node(16);
        BinaryTreeNode<Integer> left = tree(2, first, second);
        BinaryTreeNode<Integer> root = tree(1, left, node(2, 4, 3));
        assertEquals(left, lowestCommonAncestor(root, first, second));
    }

    @Test
    public void shouldReturnRootAsLCAOfNodesFromDifferentSubtrees() {
        BinaryTreeNode<Integer> first = node(18);
        BinaryTreeNode<Integer> second = node(12);
        BinaryTreeNode<Integer> left = tree(2, node(3), first);
        BinaryTreeNode<Integer> right = tree(2, node(4), second);
        BinaryTreeNode<Integer> root = tree(1, left, right);
        assertEquals(root, lowestCommonAncestor(root, first, second));
    }

    @Test
    public void shouldReturnRootAsLCAOfNodesFromDifferentLevels() {
        BinaryTreeNode<Integer> target = node(18);
        BinaryTreeNode<Integer> left = tree(2, target, node(18));
        BinaryTreeNode<Integer> right = tree(2, node(4), node(5));
        BinaryTreeNode<Integer> root = tree(1, left, right);
        assertEquals(left, lowestCommonAncestor(root, left, target));
    }

    @Test
    public void shouldFindLeftMostLeafAsSum() {
        BinaryTreeNode<Integer> tree =
                tree(1,
                        node(
                                2,
                                3,
                                4
                        ),
                        node(
                                5,
                                6,
                                7
                        )
                );
        assertTrue(hasPathWithSum(tree, 6));
    }

    @Test
    public void shouldFindRightMostLeafAsSum() {
        BinaryTreeNode<Integer> tree =
                tree(1,
                        node(
                                2,
                                3,
                                4
                        ),
                        node(
                                5,
                                6,
                                7
                        )
                );
        assertTrue(hasPathWithSum(tree, 13));
    }

    @Test
    public void shouldFindSumInTheMiddleLeaf() {
        BinaryTreeNode<Integer> tree =
                tree(1,
                        node(
                                2,
                                3,
                                4
                        ),
                        node(
                                5,
                                6,
                                7
                        )
                );
        assertTrue(hasPathWithSum(tree, 12));
    }

    @Test
    public void shouldNotFindSum() {
        BinaryTreeNode<Integer> tree =
                tree(1,
                        node(
                                2,
                                3,
                                4
                        ),
                        node(
                                5,
                                6,
                                7
                        )
                );
        assertFalse(hasPathWithSum(tree, 28));
    }

    @Test
    public void shouldReturnInOrderTraversal() {
        BinaryTreeNode<Integer> tree = tree(10,
                tree(
                        12,
                        node(3),
                        node(4, 6, 7)
                ),
                tree(
                        5,
                        node(11),
                        node(2)
                )
        );
        assertEquals(Arrays.asList(3, 12, 6, 4, 7, 10, 11, 5, 2), inOrderTraversal(tree));
    }

    @Test
    public void shouldReturnExteriorOfTree() {
        BinaryTreeNode<Integer> tree = tree(10,
                tree(
                        12,
                        node(3),
                        node(4, 6, 7)
                ),
                tree(
                        5,
                        node(11),
                        node(2)
                )
        );
        assertEquals(Arrays.asList(10, 12, 3, 6, 7, 11, 2, 10, 5), exteriorOfTree(tree));
    }

    @Test
    public void shouldComputeLeftToRightMappingInPerfectTree() {
        BinaryTreeNode<Integer> tree =
                tree(1,
                        tree(
                                2,
                                node(
                                        3,
                                        4,
                                        5
                                ),
                                node(
                                        6,
                                        7,
                                        8
                                )
                        ),
                        tree(
                                20,
                                node(
                                        23,
                                        24,
                                        25
                                ),
                                node(
                                        26,
                                        27,
                                        28
                                )
                        )
                );
        Map<Integer, Integer> valuesMapping = leftTheRightLevelMapping(tree)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().data, e -> e.getValue().data));

        assertThat(valuesMapping).containsOnly(
                entry(2, 20),
                entry(3, 6), entry(6, 23), entry(23, 26),
                entry(4, 5), entry(5, 7), entry(7, 8), entry(8, 24), entry(24, 25), entry(25, 27), entry(27, 28)
        );
    }
}
