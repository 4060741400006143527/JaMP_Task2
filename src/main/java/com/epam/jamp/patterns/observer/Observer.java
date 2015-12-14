package com.epam.jamp.patterns.observer;

public abstract class Observer {

    protected TextData textData;

    public abstract void update(String word);
}
