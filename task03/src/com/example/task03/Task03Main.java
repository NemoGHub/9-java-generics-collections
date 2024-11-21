package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        TreeMap<String, TreeSet<String>> groups = new TreeMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            List<String> input = bufferedReader.lines().map(String::toLowerCase)
                    .filter(x -> x.matches("[а-яёА-ЯЁ]+"))
                    .filter(x -> x.length() >= 3)
                    .collect(Collectors.toList());

            for (String word : input){
                char[] letters = word.toCharArray();
                Arrays.sort(letters);
                String lettersString = new String(letters);
                groups.computeIfAbsent(lettersString, x -> new TreeSet<>()).add(word);
                // Если такой нет, то computeIfAbsent() создаёт новую группу
            }
        } catch (Exception e) { throw new RuntimeException(e); }
        return groups.values()
                .stream()
                .filter(x -> x.size() >= 2)
                .collect(Collectors.toList());
    }
}
