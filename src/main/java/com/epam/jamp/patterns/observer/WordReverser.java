package com.epam.jamp.patterns.observer;

import com.epam.jamp.patterns.reader.TextReaderUtils;

public class WordReverser extends Observer {

    private String word;

    public WordReverser(TextData textData) {
        this.textData = textData;
        textData.registerObserver(this);
    }

    @Override
    public void update(String word) {
        if (!TextReaderUtils.isNumeric(word)) {
            this.word = new StringBuilder(word).reverse().toString();
        }
    }

    public String getReversedWord() {
        return word;
    }
}
