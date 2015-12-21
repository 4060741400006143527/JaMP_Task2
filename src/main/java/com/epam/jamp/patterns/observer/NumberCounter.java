package com.epam.jamp.patterns.observer;

import com.epam.jamp.patterns.reader.TextReaderUtils;

public class NumberCounter implements Observer{

    private int counter;

    @Override
    public void update(String word) {
        if (TextReaderUtils.isNumeric(word)) {
            counter++;
        }
    }

    public int getNumberCount() {
        return counter;
    }
}
