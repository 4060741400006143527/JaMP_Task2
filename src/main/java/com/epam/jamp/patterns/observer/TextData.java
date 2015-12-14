package com.epam.jamp.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class TextData implements Observable {

    private List<Observer> observers;
    private String word;

    public TextData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(word));
    }

    public void setWord(String word) {
        this.word = word;
        notifyObservers();
    }
}
