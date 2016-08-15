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
    public static <T> Node<T> from(List<T> elements) {
        if (elements == null || elements.size() == 0) return null;

        Node<T> head = new Node<>(elements.get(0));
        Node<T> current = head;
        for (int i = 1; i < elements.size(); i++) {
            current.tail = new Node<>(elements.get(i));
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

    /**
     * Merges two sorted linked lists into a sorted linked list
     *
     * @param first  sorted list
     * @param second sorted list
     * @return single sorted linked list
     */
    public static Node<Integer> sortedFromTwoSorted(Node<Integer> first, Node<Integer> second) {
        Node<Integer> dummy = new Node<>(0);
        Node<Integer> current = dummy;

        while (first != null && second != null) {
            if (first.data < second.data) {
                current.tail = first;
                first = first.tail;
            } else {
                current.tail = second;
                second = second.tail;
            }
            current = current.tail;
        }

        current.tail = (first != null) ? first : second;

        return dummy.tail;
    }

    /**
     * Reverses linked list
     *
     * @param head of linked list
     * @param <T>  type of elements
     * @return reversed linked list
     */
    public static <T> Node<T> reverse(Node<T> head) {
        if (head == null) return null;
        if (head.tail == null) return head;

        Node<T> reversed = reverse(head.tail);

        head.tail.tail = head;
        head.tail = null;

        return reversed;
    }
}
