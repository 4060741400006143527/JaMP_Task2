package com.epam.jamp.patterns.composite;

public class File implements FSEntity{
    
    private int size;
    private String name;

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
}
