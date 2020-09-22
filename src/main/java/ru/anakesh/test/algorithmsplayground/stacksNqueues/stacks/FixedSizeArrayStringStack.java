package ru.anakesh.test.algorithmsplayground.stacksNqueues.stacks;

import com.sun.istack.internal.NotNull;

import java.util.NoSuchElementException;

public class FixedSizeArrayStringStack implements Stack<String> {
    @NotNull
    private final String[] itemArray;
    private int nextFirstItemIndex = 0;

    public FixedSizeArrayStringStack(int size) {
        this.itemArray = new String[size];
    }

    @Override
    public void push(String item) {
        if(nextFirstItemIndex < itemArray.length){
            itemArray[nextFirstItemIndex++] = item;
        } else{
            throw new IndexOutOfBoundsException("Stack is full");
        }
    }

    @Override
    public String pop() {
        if(nextFirstItemIndex>0){
            String firstItem = itemArray[--nextFirstItemIndex];
            itemArray[nextFirstItemIndex] = null;
            return firstItem;
        } else{
            throw new NoSuchElementException("Stack is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return nextFirstItemIndex==0;
    }
}
