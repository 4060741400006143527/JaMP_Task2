package com.epam.jamp.patterns.adapter;

import java.util.List;

public abstract class AbstractAdapter<E, T extends List<E>>  implements Stack<E> {

    private T list;

    public AbstractAdapter(T list) {
        this.list = list;
    }

    @Override
    public void push(E e) {
        list.add(e);
    }

    public abstract E pop();

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    } 
}
