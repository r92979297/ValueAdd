package com.rja;

class WordOccurrence {
    private  final String word;
    private final long lineOfOccurrence;

    public WordOccurrence(String word, long lineOfOccurrence) {
        this.word =word;
        this.lineOfOccurrence = lineOfOccurrence;
    }

    public String getWord() {
        return word;
    }

    public long getLineOfOccurrence() {
        return lineOfOccurrence;
    }
}