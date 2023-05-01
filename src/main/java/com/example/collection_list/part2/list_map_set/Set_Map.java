package com.example.collection_list.part2.list_map_set;

import java.util.*;



//Задание 1.

public class Set_Map {
    private static final List<Integer> nums = new ArrayList<>(List.of(1, 1, 2, 3, 4, 4, 5, 5, 6, 7));
    private static final List<String> words = new ArrayList<>(List.of("abc", "e", "abc", "abca", "aabc", "abcc", "abc", "e", "e", "e"));
    private static final List<String> words2 = new ArrayList<>(List.of("один", "два", "два", "два", "три", "три", "три","три"));


    public static void main(String[] args) {
        printNums();
        System.out.println("==============");
        printNums2();
        System.out.println("==============");
        printUniqWords();
        System.out.println("==============");
        printUniqWords2();
        System.out.println("==============");

        printCountWords();
        System.out.println("==============");


    }

    public static void printNums() {
        for (int num : nums) {
            if (num % 2 != 0) {
                System.out.println(num);
            }
        }

    }

    public static void printNums2() {
        Collections.sort(nums);
        int prevNum = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num % 2 == 0 && num != prevNum) {
                System.out.println(num);
                prevNum = num;
            }
        }
    }

    public static void printUniqWords() {
        Set<String> uniqWord = new HashSet<>(words);
        System.out.println(uniqWord);
    }

    public static void printUniqWords2() {
        Set<String> uniqWord = new HashSet<>(words);
        System.out.println(words.size() - uniqWord.size());
    }

    public static void printCountWords() {
        Map<String,Integer> map =new LinkedHashMap<>();
        for (String word : words2) {

           if(!map.containsKey(word)){
                map.put(word, 0);
            }
            if(map.containsKey(word)){
                map.put(word, map.get(word)+1);
            }
        }
        System.out.println(map.values());
        //Безобидный коментарий \u000a System.out.println("Bugaga");
    }



}









