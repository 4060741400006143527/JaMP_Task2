package com.epam.jamp.patterns.adapter;

import java.util.Stack;

public class StackAdapter<E> extends AbstractAdapter<E, Stack<E>>{

    public StackAdapter() {
        super(new Stack<>());
    }

   @Override
    public E pop() {
       return getList().pop();
    }
}
