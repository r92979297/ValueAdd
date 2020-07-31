package com.rja;

import java.util.List;
import java.util.stream.Collectors;

class WordSummary {
    private final String word;
    private List<Long> linesOfOccurrence;

    public WordSummary(String word, List<WordOccurrence> lineOfOccurrence) {
        verify(word, lineOfOccurrence);
        this.word = word;
        this.linesOfOccurrence = lineOfOccurrence.stream()
                .map(WordOccurrence::getLineOfOccurrence)
                .collect(Collectors.toList());
    }

    private void verify(String word, List<WordOccurrence> lineOfOccurrence) {
        if(word ==null || word.isBlank() || lineOfOccurrence ==null)
            throw new IllegalArgumentException(word);

        boolean isConsistent = lineOfOccurrence.stream()
                .allMatch(wordOccurrence -> wordOccurrence.getWord().equals(word));
        if(!isConsistent)
            throw new IllegalArgumentException("Lista wystąpień dotyczny różnych słów");
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word + " - "+ linesOfOccurrence.size()+" - pozycje -> " + linesOfOccurrence;
    }
}
