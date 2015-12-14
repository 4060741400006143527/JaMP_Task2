package com.epam.jamp.patterns.adapter;

public interface CollectionAdapter<E> {

    /**
     * appends a given object
     */
    void push(E e);

    /**
     * pulls the last object from the collection
     */
    E pop();
}
