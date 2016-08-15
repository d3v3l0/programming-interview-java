package com.github.tkachuko.origramming.interview.lists;

import java.util.LinkedList;
import java.util.List;

public class Lists {

    /**
     * Creates linked list from given values
     *
     * @param elements values in form of array
     * @param <T>      type of elements
     * @return linked list out of elements
     */
    public static <T> Node<T> from(T... elements) {
        if (elements == null || elements.length == 0) return null;

        Node<T> head = new Node<>(elements[0]);
        Node<T> current = head;
        for (int i = 1; i < elements.length; i++) {
            current.tail = new Node<>(elements[i]);
            current = current.tail;
        }
        return head;
    }

    /**
     * Converts linked list to java list
     *
     * @param head of linked list
     * @param <T>  type of elements in list
     * @return java collection
     */
    public static <T> List<T> toJavaList(Node<T> head) {
        List<T> list = new LinkedList<>();
        while (head != null) {
            list.add(head.data);
            head = head.tail;
        }
        return list;
    }
}
