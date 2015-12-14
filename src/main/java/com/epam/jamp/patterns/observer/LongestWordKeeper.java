package com.epam.jamp.patterns.observer;

import com.epam.jamp.patterns.reader.TextReaderUtils;

public class LongestWordKeeper extends Observer{

    private String longestWord;
    private int maxLength = 0;

    public LongestWordKeeper(TextData textData) {
        this.textData = textData;
        textData.registerObserver(this);
    }

    @Override
    public void update(String word) {
        if (!TextReaderUtils.isNumeric(word)) {
           if(maxLength < word.length()){
               longestWord = word;
           }
        }
    }

    public String getLongestWord(){
        return longestWord;
    }
}
