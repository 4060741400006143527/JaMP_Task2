package com.epam.jamp.patterns.observer;

import com.epam.jamp.patterns.reader.TextReaderUtils;

public class LongestWordKeeper implements Observer{

    private String longestWord;
    private int maxLength = 0;

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
