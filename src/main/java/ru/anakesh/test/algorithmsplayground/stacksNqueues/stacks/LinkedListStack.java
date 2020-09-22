package ru.anakesh.test.algorithmsplayground.stacksNqueues.stacks;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {
    @Nullable
    private Node first = null;

    @Override
    public void push(T item) {
        first = new Node(item, first);
    }

    @Override
    public T pop() {
        if(first!=null) {
            T popItem = first.item;
            first = first.next;
            return popItem;
        } else{
            throw new NoSuchElementException("Stack is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

    @Data
    @AllArgsConstructor
    private class Node{
        private final T item;
        @Nullable
        private final Node next;

    }
}
