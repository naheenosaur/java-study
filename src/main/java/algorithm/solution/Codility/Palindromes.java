package algorithm.solution.Codility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://app.codility.com/programmers/task/palindromes/
/*
A few palindromic sentences exist in many languages.
They look the same, regardless of whether they are read normally (from left to right) or backwards (from right to left).
Here are some English examples:

was it a rat i saw
mr owl ate my metal worm
live on time emit no evil
Precisely speaking, a sentence is palindromic if, after removing all its spaces (and punctuation),
it looks the same when read both left to right and right to left.
For example, the second example sentence would become "mrowlatemymetalworm", which looks exactly the same when spelled backwards.

Tom just found a list of words in an old language, and he wonders if he can use them to create a palindromic sentence.
Since he doesn't know the language, he will have to assume that any sequence of words from the list is a valid sentence.
In order to create a palindromic sentence, he can use each word as many times as he wants.
In particular, he may decide not to use some words at all.

Write a function:

class Solution { public String solution(String S); }

that, given a string S of length N containing a space-separated list of all the words in the language,
returns any palindromic sentence built from the words in S.
All of the words in the output sentence should be written in lower case and separated by single spaces.
If no such sentence exists, the function should return the word "NO".
The length of the sentence should not exceed 600,000 characters.
You can assume that if a palindromic sentence can be constructed using the input words,
then a palindromic sentence of length not greater than 600,000 characters can be constructed as well.

Examples:

1. Given S="by metal owl egg mr crow worm my ate", your function may return "mr owl ate my metal worm", "mr owl worm", or any other palindromic sentence built using the words from the list, that does not exceed 600,000 characters.

2. Given S="live on time emit no evil", your function may for example return "live on time emit no evil", "no on on no no on", "evil time emit live", or any other palindromic sentence built using the words from the list,
that does not exceed 600,000 characters.

3. Given S="abcc bc ac", your function should return "NO", since no palindromic sentence may be constructed using words from the list.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..500];
string S consists only of lowercase letters (a−z) and spaces.
 */
public class Palindromes {
    public static void main(String[] args) {
        System.out.println(solution("by metal owl egg mr crow worm my ate"));
        System.out.println(solution("live on time emit no evil"));
        System.out.println(solution("abcc bc ac"));
    }

    public static String solution(String S) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Set<String>> startMap = new HashMap<>();
        Map<Character, Set<String>> endMap = new HashMap<>();
        for (char s : S.toCharArray()) {
            if (s == ' ') {
                if (putStartAndEndMap(startMap, endMap, sb)) {
                    return sb.toString();
                }
                sb = new StringBuilder();
                continue;
            }
            sb.append(s);
        }

        if (putStartAndEndMap(startMap, endMap, sb)) {
            return sb.toString();
        }

        //        startMap.forEach((startChar, startWords) -> {
        //            Set<String> endWords = endMap.getOrDefault(startChar, null);
        //            if ( endWords != null ) {
        //                startWords.forEach(startWord -> {
        //                    endWords.stream().filter(endWord -> startWord.contains(endWord)).map(endWord -> endWord.substring(endWord.length(), startWord.length()));
        //                    endWords.stream().filter(endWord -> endWord.contains(startWord)).map(endWord -> endWord.substring(endWord.length(), startWord.length()));
        //                    });
        //                });
        //            }
        //        });
        return sb.toString();
    }

    public static boolean putStartAndEndMap(Map<Character, Set<String>> startMap, Map<Character, Set<String>> endMap, StringBuilder sb) {
        Set<String> startSet = startMap.getOrDefault(sb.charAt(0), new HashSet<>());
        if (startSet.contains(sb.toString())) {
            return true;
        }
        startSet.add(sb.toString());
        startMap.put(sb.charAt(0), startSet);

        StringBuilder sbReverse = sb.reverse();
        if (sb == sbReverse) {
            return true;
        }

        Set<String> endSet = endMap.getOrDefault(sbReverse.charAt(0), new HashSet<>());
        endSet.add(sbReverse.reverse().toString());
        endMap.put(sbReverse.charAt(0), endSet);
        return false;
    }
}
