package com.epam.jamp.patterns.adapter;

import java.util.ArrayList;
import java.util.List;

public class ArrayListAdapter<E> extends AbstractAdapter<E> {

    public ArrayListAdapter(ArrayList<E> arrayList) {
       super(arrayList);
    }

    @Override
    public E pop() {
        List<E> list = getList();
        return list.get(list.size() - 1);
    }
}
