package com.epam.jamp.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FSEntity {

    private int size;
    private String name;
    private List<FSEntity> components;

    public Directory() {
        components = new ArrayList<>();
        size = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    public void add(FSEntity component) {
        components.add(component);
        size = size + component.getSize();
    }

    public void remove(FSEntity component) {
        components.remove(component);
        size = size - component.getSize();
    }
}
