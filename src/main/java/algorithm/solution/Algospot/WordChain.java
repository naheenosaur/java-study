package algorithm.solution.Algospot;

/*
문제
끝말잇기는 참가자들이 원을 그리고 앉은 뒤, 시계 방향으로 돌아가면서 단어를 말하는 게임입니다.
이 때 각 사람이 말하는 단어의 첫 글자는 이전 사람이 말한 단어의 마지막 글자와 같아야 합니다.
단어 제한 끝말잇기는 일반적인 끝말잇기와 달리 사용할 수 있는 단어의 종류가 게임 시작 전에 미리 정해져 있으며, 한 단어를 두 번 사용할 수 없습니다.
단어 제한 끝말잇기에서 사용할 수 있는 단어들의 목록이 주어질 때, 단어들을 전부 사용하고 게임이 끝날 수 있는지,
그럴 수 있다면 어떤 순서로 단어를 사용해야 하는지를 계산하는 프로그램을 작성하세요.

입력
입력의 첫 줄에는 테스트 케이스의 수 C (1 <= C <= 50) 가 주어집니다.
각 테스트 케이스의 첫 줄에는 게임에서 사용할 수 있는 단어의 수 N (1 <= N <= 100) 이 주어집니다.
그 후 N 줄에 하나씩 게임에서 사용할 수 있는 단어가 주어집니다. 각 단어는 알파벳 소문자로 구성되어 있으며, 길이는 2 이상 10 이하입니다.
중복으로 출현하는 단어는 없습니다.

출력
각 테스트 케이스마다 한 줄을 출력합니다.
만약 모든 단어를 사용하고 게임이 끝나는 방법이 없다면 "IMPOSSIBLE" 을 출력하고 (따옴표 제외),
방법이 있다면 사용할 단어들을 빈 칸 하나씩을 사이에 두고 순서대로 출력합니다.
방법이 여러 개 있다면 그 중 아무 것이나 출력해도 좋습니다.

예제 입력
1
5
cc
aa
ab
bb
bc

예제 출력
IMPOSSIBLE
aa ab bb
need dog god dragon
need dog god dragon

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WordChain {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer testCase = Integer.parseInt(bufferedReader.readLine());

        for (int num = 0; num < testCase; num++) {
            LinkedList<String> resultWordQueue = new LinkedList<>();
            List<String> inputWordList = new ArrayList<>();
            Map<Character, Set<String>> latterStartWordMap = new HashMap<>();
            Integer wordCase = Integer.parseInt(bufferedReader.readLine());

            if (wordCase == 1) {
                System.out.println(bufferedReader.toString());
                continue;
            }

            for (int wordNum = 0; wordNum < wordCase; wordNum++) {
                String word = bufferedReader.readLine();
                inputWordList.add(word);
                addWord(latterStartWordMap, word);
            }
            for (int i = 0; i < inputWordList.size(); i++) {
                resultWordQueue.add(inputWordList.get(i));
                removeWord(latterStartWordMap, inputWordList.get(i));
                resultWordQueue = travel(latterStartWordMap, resultWordQueue, wordCase);
                if (resultWordQueue != null) {
                    break;
                } else {
                    resultWordQueue = new LinkedList<>();
                }
                addWord(latterStartWordMap, inputWordList.get(i));
            }
            if (resultWordQueue.size() == 0) {
                System.out.println("IMPOSSIBLE");
            } else {
                printStrings(resultWordQueue);
            }
        }
    }

    public static LinkedList<String> travel(Map<Character, Set<String>> latterStartWordMap, LinkedList<String> resultWordQueue, int wordCase) {
        if (resultWordQueue.size() == wordCase) {
            return resultWordQueue;
        }
        String now = resultWordQueue.peekLast();
        Set<String> latterStartWordSet = latterStartWordMap.get(now.charAt(now.length() - 1));
        if (latterStartWordSet == null) {
            // 이거라면 set에 다시 추가해 줌
            return null;
        }
        for (String word : latterStartWordSet) {
            removeWord(latterStartWordMap, word);
            resultWordQueue.add(word);
            if (resultWordQueue.size() == 0) {
                resultWordQueue.add(word);
            }
            LinkedList<String> result = travel(latterStartWordMap, resultWordQueue, wordCase);
            if (result != null) {
                return result;
            }
            addWord(latterStartWordMap, word);
        }
        return null;
    }

    public static void addWord(Map<Character, Set<String>> latterStartWordMap, String word) {
        latterStartWordMap.putIfAbsent(word.charAt(0), new HashSet<>());
        Set<String> startWordSet = latterStartWordMap.get(word.charAt(0));
        startWordSet.add(word);
        latterStartWordMap.replace(word.charAt(0), startWordSet);
    }

    public static void removeWord(Map<Character, Set<String>> latterStartWordMap, String word) {
        Set<String> startWordSet = latterStartWordMap.get(word.charAt(0));
        startWordSet.remove(word);
        latterStartWordMap.replace(word.charAt(0), startWordSet);
    }

    public static void printStrings(LinkedList<String> result) {
        while (result.size() > 1) {
            System.out.print(result.pollFirst() + " ");
        }
        System.out.println(result.pollFirst());
    }
}
