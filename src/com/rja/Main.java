package com.rja;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Main {

    public static final Collator collator= Collator.getInstance(new Locale("pl","PL"));

    public static void main(String[] args) {

        try (BufferedReader br = Files.newBufferedReader(Paths.get("resources//zadanie.txt"))) {

            List<WordOccurrence> wordOccurrences = new ArrayList<>();
            List<String> lines = br.lines().collect(toList());

            for (int i = 0; i < lines.size(); i++) {
                wordOccurrences.addAll(splitLineToWords(lines.get(i),i+1));
            }

            wordOccurrences.stream()
                    .collect(groupingBy(WordOccurrence::getWord,toList()))
                    .entrySet()
                    .stream()
                    .map(entry -> new WordSummary(entry.getKey(),entry.getValue()))
                    .sorted(Comparator.comparing(WordSummary::getWord,collator ))
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<WordOccurrence> splitLineToWords(String line, long lineNumber){
        return  Stream.of(line.split("[^\\p{IsAlphabetic}0-9']+"))
                .filter(w-> !w.trim().isBlank())
                .filter(Pattern.compile("\\D+").asPredicate())
                .map(String::toLowerCase)
                .map(w-> new WordOccurrence(w,lineNumber)).
                        collect(Collectors.toList());
    }
}
