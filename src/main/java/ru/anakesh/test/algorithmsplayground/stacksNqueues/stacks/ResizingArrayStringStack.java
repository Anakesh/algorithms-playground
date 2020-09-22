package ru.anakesh.test.algorithmsplayground.stacksNqueues.stacks;

import com.sun.istack.internal.NotNull;

import java.util.NoSuchElementException;

public class ResizingArrayStringStack implements Stack<String> {
    @NotNull
    private String[] itemArray = new String[1];
    private int nextFirstItemIndex = 0;

    @Override
    public void push(String item) {
        if(nextFirstItemIndex ==itemArray.length){
            resize(itemArray.length*2);
        }
        itemArray[nextFirstItemIndex++] = item;
    }

    @Override
    public String pop() {
        if(nextFirstItemIndex>0){
            String firstItem = itemArray[--nextFirstItemIndex];
            itemArray[nextFirstItemIndex] = null;
            if(nextFirstItemIndex>0 && nextFirstItemIndex==itemArray.length/4) resize(itemArray.length/2);
            return firstItem;
        } else{
            throw new NoSuchElementException("Stack is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return nextFirstItemIndex==0;
    }

    private void resize(int newSize) {
        String[] newItemArray = new String[newSize];
        System.arraycopy(itemArray, 0, newItemArray, 0, itemArray.length);
        itemArray = newItemArray;
    }
}
