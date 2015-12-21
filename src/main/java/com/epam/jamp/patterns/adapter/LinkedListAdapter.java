package com.epam.jamp.patterns.adapter;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class LinkedListAdapter<E> extends AbstractAdapter<E, LinkedList<E>> {

    public LinkedListAdapter() {
        super(new LinkedList<>());
    }

    @Override
    public E pop() {
        LinkedList<E> list = getList();
        if (list.isEmpty()){
            throw new EmptyStackException();
        }
        return list.getLast();
    }
}
