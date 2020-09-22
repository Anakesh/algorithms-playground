package ru.anakesh.test.algorithmsplayground.stacksNqueues.stacks;

import com.sun.istack.internal.NotNull;

public interface Stack<T> {

    void push(T item);

    T pop();

    boolean isEmpty();
}
