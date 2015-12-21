package com.epam.jamp.patterns.observer;

import com.epam.jamp.patterns.reader.TextReaderUtils;

public class WordCounter implements Observer {

    private int counter;

    @Override
    public void update(String word) {
        if (!TextReaderUtils.isNumeric(word)) {
            counter++;
        }
    }

    public int getWordCount() {
        return counter;
    }
}
