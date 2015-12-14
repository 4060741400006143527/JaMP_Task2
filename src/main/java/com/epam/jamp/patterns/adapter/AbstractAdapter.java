package com.epam.jamp.patterns.adapter;

import java.util.List;

public abstract class AbstractAdapter<E> implements CollectionAdapter<E> {

    private List<E> list;

    public AbstractAdapter(List<E> list) {
        this.list = list;
    }

    @Override
    public void push(E e) {
        list.add(e);
    }

    public abstract E pop();

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}
