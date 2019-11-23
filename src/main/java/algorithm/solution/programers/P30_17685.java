package algorithm.solution.programers;
/*
자동완성
포털 다음에서 검색어 자동완성 기능을 넣고 싶은 라이언은 한 번 입력된 문자열을 학습해서 다음 입력 때 활용하고 싶어 졌다. 예를 들어, go 가 한 번 입력되었다면, 다음 사용자는 g 만 입력해도 go를 추천해주므로 o를 입력할 필요가 없어진다! 단, 학습에 사용된 단어들 중 앞부분이 같은 경우에는 어쩔 수 없이 다른 문자가 나올 때까지 입력을 해야 한다.
효과가 얼마나 좋을지 알고 싶은 라이언은 학습된 단어들을 찾을 때 몇 글자를 입력해야 하는지 궁금해졌다.

예를 들어, 학습된 단어들이 아래와 같을 때

go
gone
guild
go를 찾을 때 go를 모두 입력해야 한다.
gone을 찾을 때 gon 까지 입력해야 한다. (gon이 입력되기 전까지는 go 인지 gone인지 확신할 수 없다.)
guild를 찾을 때는 gu 까지만 입력하면 guild가 완성된다.
이 경우 총 입력해야 할 문자의 수는 7이다.

라이언을 도와 위와 같이 문자열이 입력으로 주어지면 학습을 시킨 후, 학습된 단어들을 순서대로 찾을 때 몇 개의 문자를 입력하면 되는지 계산하는 프로그램을 만들어보자.

입력 형식
학습과 검색에 사용될 중복 없는 단어 N개가 주어진다.
모든 단어는 알파벳 소문자로 구성되며 단어의 수 N과 단어들의 길이의 총합 L의 범위는 다음과 같다.

2 <= N <= 100,000
2 <= L <= 1,000,000
출력 형식
단어를 찾을 때 입력해야 할 총 문자수를 리턴한다.

입출력 예제
words	result
[go,gone,guild]	7
[abc,def,ghi,jklm]	4
[word,war,warrior,world]	15
입출력 설명
첫 번째 예제는 본문 설명과 같다.
두 번째 예제에서는 모든 단어들이 공통된 부분이 없으므로, 가장 앞글자만 입력하면 된다.
세 번째 예제는 총 15 자를 입력해야 하고 설명은 아래와 같다.
word는 word모두 입력해야 한다.
war는 war 까지 모두 입력해야 한다.
warrior는 warr 까지만 입력하면 된다.
world는 worl까지 입력해야 한다. (word와 구분되어야 함을 명심하자)
 */

import java.util.Arrays;
import java.util.LinkedList;

public class P30_17685 {
    public static void main(String[] args) {
        String[] words = {"go", "gone", "guild"}; // 7
        //        String[] words = {"abc", "def", "ghi", "jklm"}; // 4
        //        String[] words = {"word", "war", "warrior", "warriors", "world"}; // 26
        //        String[] words = {"word", "war", "warrior", "world"}; // 15
        //        String[] words = {"a", "b", "c", "d", "e"}; //5
        //        String[] words = {"a", "ab", "ac", "ad", "ae"}; //9
        //        String[] words = {"aa", "ab", "ac", "ad", "ae"}; //10
        System.out.println(solution(words));
    }

    /*
    war, warrior, warriors, word, world, worse  -> 3, 7, 8, 4, 4, 4
                   target

    --> index = 0 부터 다른것 까지 하나씩 탐색

    w a r ( 3 )  --> current.length이면 index 반환 ( 다음 글자로 넘어감 )
    w a r r  [ 4 ] --> length + 1 을 temp index

    w a r r --> temp index 이 같은 경우 temp index 부터 하나씩 탐색
    w a r r

    w a r r i o r ( 4 --> 7 )  --> current.length이면 index 반환 ( 다음 글자로 넘어감 )
    w a r r i o r s [ 8 ] --> length + 1 을 temp index

    w a r r i o r s ( 8 -> 8 ) --> next 의 길이가 current 보다 작으면 temp index 반환  ( 다음 글자로 넘어감 )
    w o r d

    --> index = 0 부터 다른것 까지 하나씩 탐색

    w o r d ( 4 )  --> 차이가 나는 값 index 반환 ( 다음 글자로 넘어감 )
    w o r l [ 4 ]  --> index를 temp index

    w o r l ( 4 -> 4 ) --> 차이가 나는 값 index 반환 ( 다음 글자로 넘어감 )
    w o r s --> index를 temp index

    end ------> ( 4 -> 4 ) --> 종료 시 temp index 반환
     */

    // solution2 : stringBuilder List를 만들어도 되겠다?
    public static int solution(String[] words) {
        Arrays.sort(words);

        StringBuilder current1 = new StringBuilder();
        StringBuilder current2 = new StringBuilder();

        int current1Length = 0;
        while (words[1].startsWith(current1.toString())) { // 하나씩 올라감
            if (current1Length == words[0].length()) {
                current2.append(words[1].charAt(words[0].length()));
                break;
            }
            current1.append(words[0].charAt(current1Length));
            current2.append(words[1].charAt(current1Length++));
        }

        LinkedList<StringBuilder> wordSbList = new LinkedList<>();
        wordSbList.add(current1);
        wordSbList.add(current2);

        // list의 가장 뒤에있는것 가져옴, 그거랑 다음꺼 비교.. 하나씩 올라가던지 하나씩 내려가던지?
        for (int i = 1; i < words.length - 1; i++) {
            String currentString = words[i];
            String targetString = words[i + 1];
            StringBuilder currentStringBuilder = new StringBuilder(wordSbList.getLast());
            StringBuilder targetStringBuilder = new StringBuilder(currentStringBuilder);

            if (targetString.startsWith(currentStringBuilder.toString())) {
                int wordLength = currentStringBuilder.length();

                while (targetString.startsWith(currentStringBuilder.toString())) { // 하나씩 올라감
                    if (wordLength == currentString.length()) {
                        targetStringBuilder.append(targetString.charAt(currentString.length()));
                        break;
                    }
                    currentStringBuilder.append(currentString.charAt(wordLength));
                    targetStringBuilder.append(targetString.charAt(wordLength++));
                }
                wordSbList.removeLast();
                wordSbList.add(currentStringBuilder);
            } else {

                while (!targetString.startsWith(targetStringBuilder.toString())) { // 하나씩 내려감
                    targetStringBuilder.deleteCharAt(targetStringBuilder.length() - 1);
                }
                targetStringBuilder.append(targetString.charAt(targetStringBuilder.length()));
            }
            wordSbList.add(targetStringBuilder);
        }

        int answer = 0;
        for (StringBuilder aWordSbList : wordSbList) {
            answer += aWordSbList.length();
        }
        return answer;
    }
}
