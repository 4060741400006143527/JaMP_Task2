package com.epam.jamp.patterns.adapter;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class ArrayListAdapter<E> extends AbstractAdapter<E, ArrayList<E>> {

    public ArrayListAdapter() {
       super(new ArrayList<>());
    }

    @Override
    public E pop() {
        List<E> list = getList();
        if (list.isEmpty()){
            throw new EmptyStackException();
        }
        E lastElement = list.get(list.size() - 1);

        list.remove(lastElement);
        return lastElement;
    }
}
