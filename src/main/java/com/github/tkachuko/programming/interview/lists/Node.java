package com.github.tkachuko.programming.interview.lists;

/**
 * Very simple implementation of linked list in Java
 *
 * @param <T> type of elements inside of list
 */
public class Node<T> {

    public T data;
    public Node<T> tail;

    public Node(T data, Node<T> tail) {
        this.data = data;
        this.tail = tail;
    }

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
