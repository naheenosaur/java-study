package algorithm.solution.programers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class P30_17686 {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        System.out.println(Arrays.toString(solution(files)));
    }

    public static String[] solution(String[] files) {

        List<Word> wordList = new ArrayList<>();
        for (String word : files) {
            String[] strings = word.split("[0-9]+", 2);
            String head = strings[0].toLowerCase();
            int startIndex = head.length();
            int endIndex;
            if (strings.length == 1) {
                endIndex = word.length();
            } else {
                endIndex = word.length() - strings[1].length();
            }
            if (endIndex > startIndex + 5) {
                endIndex = startIndex + 5;
            }
            int number = Integer.parseInt(word.substring(startIndex, endIndex));
            wordList.add(new Word(word, head, number));
        }

        wordList.sort(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if (o1.head.compareTo(o2.head) < 0) {
                    return -1;
                } else if (o1.head.equals(o2.head)) {
                    if (o1.number < o2.number) {
                        return -1;
                    } else if (o1.number == o2.number) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        });

        String[] answer = new String[wordList.size()];
        answer = wordList.stream().map(word -> word.word).collect(Collectors.toList()).toArray(answer);

        return answer;
    }

    static class Word {
        String word;
        String head;
        Integer number;

        Word(String word, String head, Integer number) {
            this.word = word;
            this.head = head;
            this.number = number;
        }
    }
}
