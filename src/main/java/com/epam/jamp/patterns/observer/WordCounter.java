package com.epam.jamp.patterns.observer;

import com.epam.jamp.patterns.reader.TextReaderUtils;

public class WordCounter extends Observer {

    private int counter;

    public WordCounter(TextData textData) {
        this.textData = textData;
        textData.registerObserver(this);
    }

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
