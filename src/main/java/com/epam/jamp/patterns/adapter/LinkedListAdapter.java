package com.epam.jamp.patterns.adapter;

import java.util.LinkedList;

public class LinkedListAdapter<E> extends AbstractAdapter<E> {

    public LinkedListAdapter(LinkedList<E> arrayList) {
        super(arrayList);
    }

    @Override
    public E pop() {
        LinkedList<E> list = (LinkedList<E>) getList();
        return list.getLast();
    }
}
