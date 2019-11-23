package algorithm.solution.programers;

/*
단어 퍼즐은 주어진 단어 조각들을 이용해서 주어진 문장을 완성하는 퍼즐입니다.
이때, 주어진 각 단어 조각들은 각각 무한개씩 있다고 가정합니다.
예를 들어 주어진 단어 조각이 [“ba”, “na”, “n”, “a”]인 경우 ba, na, n, a 단어 조각이 각각 무한개씩 있습니다.
이때, 만들어야 하는 문장이 “banana”라면 “ba”, “na”, “n”, “a”의 4개를 사용하여 문장을 완성할 수 있지만,
“ba”, “na”, “na”의 3개만을 사용해도 “banana”를 완성할 수 있습니다.
사용 가능한 단어 조각들을 담고 있는 배열 strs와 완성해야 하는 문자열 t가 매개변수로 주어질 때,
주어진 문장을 완성하기 위해 사용해야 하는 단어조각 개수의 최솟값을 return 하도록 solution 함수를 완성해 주세요.
만약 주어진 문장을 완성하는 것이 불가능하면 -1을 return 하세요.

제한사항
strs는 사용 가능한 단어 조각들이 들어있는 배열로, 길이는 1 이상 100 이하입니다.
strs의 각 원소는 사용 가능한 단어조각들이 중복 없이 들어있습니다.
사용 가능한 단어 조각들은 문자열 형태이며, 모든 단어 조각의 길이는 1 이상 5 이하입니다.
t는 완성해야 하는 문자열이며 길이는 1 이상 20,000 이하입니다.
모든 문자열은 알파벳 소문자로만 이루어져 있습니다.
입출력 예
strs	t	result
[ba,na,n,a]	banana	3
[app,ap,p,l,e,ple,pp]	apple	2
[ba,an,nan,ban,n]	banana	-1
입출력 예 설명
입출력 예 #1
문제의 예시와 같습니다.

입출력 예 #2
ap 1개, ple 1개의 총 2개로 apple을 만들 수 있으므로 필요한 단어 개수의 최솟값은 2를 return 합니다.

입출력 예 #3
주어진 단어로는 banana를 만들 수 없으므로 -1을 return 합니다.

 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P30_12983 {
    public static void main(String[] args) {
        int a = 17;
        String[] arr = {"ba", "na", "n", "a"};
        String[] arr2 = {"app", "ap", "p", "l", "e", "ple", "pp"};
        String[] arr3 = {"ba", "an", "nan", "ban", "n"};
        System.out.println(solution(arr, "banana")); // 3
        System.out.println(solution(arr2, "apple")); // 2
        System.out.println(solution(arr3, "banana")); // -1
    }

    public static int solution(String[] str, String target) {
        // 1. 첫 글자로 시작할 수 있는 것 부터 시작
        Map<Character, Set<String>> wordList = new HashMap<>();
        for (String string : str) {
            Set<String> first = wordList.get(string.charAt(0));
            if (first == null) {
                first = new HashSet<>();
            }
            first.add(string);
            wordList.put(string.charAt(0), first);
        }

        // DFS 로 시작
        // 1. target 의 첫 단어부터 시작
        // 2. 끝나는 단어 인덱스를 [] 로 값 보관.. -> 몇개 걸렸는지, 더 작은거 있으면 더 작은거로 바꾸기

        int[] traveled = new int[target.length()];

        wordList.get(target.charAt(0)).stream().filter(word -> target.startsWith(word)).forEach(word -> {
            int dest = word.length() - 1;
            traveled[dest] = 1;
        });

        for (int i = 1; i < target.length(); i++) {
            char start = target.charAt(i);
            if (traveled[i - 1] == 0) {
                continue;
            }
            int index = i;
            wordList.get(start).stream().filter(word -> target.startsWith(word, index)).forEach(word -> {
                int dest = index + word.length() - 1;
                if (traveled[dest] == 0 || traveled[dest] > traveled[index - 1]) {
                    traveled[dest] = traveled[index - 1] + 1;
                }
            });
        }
        if (traveled[target.length() - 1] == 0) {
            return -1;
        } else {
            return traveled[target.length() - 1];
        }
    }
}
