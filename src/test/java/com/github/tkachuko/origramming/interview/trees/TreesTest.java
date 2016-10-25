package com.github.tkachuko.origramming.interview.trees;

import org.junit.Test;

import static com.github.tkachuko.origramming.interview.trees.BinaryTreeNode.node;
import static com.github.tkachuko.origramming.interview.trees.BinaryTreeNode.tree;
import static com.github.tkachuko.origramming.interview.trees.Trees.isBalanced;
import static com.github.tkachuko.origramming.interview.trees.Trees.isSymmetric;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}
