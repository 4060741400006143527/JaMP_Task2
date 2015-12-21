package com.epam.jamp.patterns.adapter;

import java.util.ArrayList;
import java.util.List;

public class ArrayListAdapter<E> extends AbstractAdapter<E, ArrayList<E>> {

    public ArrayListAdapter() {
       super(new ArrayList<>());
    }

    @Override
    public E pop() {
        List<E> list = getList();
        return list.get(list.size() - 1);
    }
}
