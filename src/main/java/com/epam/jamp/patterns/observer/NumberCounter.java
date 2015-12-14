package com.epam.jamp.patterns.observer;

import com.epam.jamp.patterns.reader.TextReaderUtils;

public class NumberCounter extends Observer{

    private int counter;

    public NumberCounter(TextData textData) {
        this.textData = textData;
        textData.registerObserver(this);
    }

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
