/*
ICPC(Individual Coders Programming Contest) is an annual and international contest sponsored and hosted by Algospot.
This year, an overwhelming number of problems were proposed for the contest, and the judges decided to use all of them.
As a result, the scoreboard table layout were broken due to large number of columns. T
o resolve this problem, judges decided to
abbreviate the coders handle(ID).

The abbreviated handle must be a prefix of the coder’s original handle,
and it should not be a prefix of any other coder’s abbreviated handle.
Furthermore, judges may choose one special coder and display his/her name as an empty string of length 0.
Though an empty string is a prefix of any coder’s abbreviated handle, we will accept it as a special case.
The problem is to minimize the sum of the length of abbreviated handles.
For example, assume that there are three coders “jm”, “joe”, and “ainu”.
If judges choose “ainu”, the abbreviated handles will be “jm”, “jo”, and “” (empty string) with total length of 4.
However, if judges choose “joe”, the abbreviated handles will be “j”, “” and “a” with total length 2, and 2
is the minimum length we can achieve.
Given the list of the coders handle, write a program to compute the minimum total abbreviated length.
You may assume that the handles are distinct, and there is no handle that is prefix of any other coder’s handle.
 */


/*
예제 입력
6
4
q
w
e
r
5
asdqwe
ertdfg
zcad
rwqe
gggg
10
GenerateParentheses
asf
ate
att
awe
awt
agag
ert
eyey
w
3
jm
joe
ainu
4
libe
petr
per
lewhazero
5
jongman
jthread
ltctl
ltdtl
ltetl
예제 출력
3
4
21
2
5
10
 */
package algorithm.solution.Algospot;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {
    public static Integer travelNode(TrieNode current) {
        Integer count = 0;
        if (current.cnt == 1) {
            return current.depth;
        }
        for (TrieNode node : current.getChildren().values()) {
            count += travelNode(node);
        }
        return count;
    }

    public static class TrieNode {
        private Map<Character, TrieNode> children = new HashMap<>();
        private TrieNode parent;
        private Integer depth;
        private Integer cnt;

        public TrieNode() {
            this.parent = null;
            this.depth = 0;
            this.cnt = 1;
        }

        public TrieNode(TrieNode node) {
            this.parent = node;
            this.cnt = 1;
            this.depth = parent.depth + 1;
        }

        Map<Character, TrieNode> getChildren() {
            return children;
        }

        void subCount() {
            this.cnt--;
        }

        void addCount() {
            this.cnt++;
        }
    }

    //    public static void main (String[] args) throws IOException {
    //        Scanner scanner = new Scanner(System.in);
    //        int TestCase = scanner.nextInt();
    ////        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    ////        int TestCase = Integer.parseInt(bufferedReader.readLine());
    //        for ( int test = 0; test < TestCase; test++ ) {
    ////            int playerNum = Integer.parseInt(bufferedReader.readLine());
    //            Trie trie = new Trie();
    //            List<String> users = new ArrayList<>();
    //            Integer min = 987654321; // 최대값 = 500 * 500 = 250000
    //            for ( int user = 0; user < playerNum; user++ ) {
    ////                String str = bufferedReader.readLine();
    //                users.add(str);
    //                trie.insert(str);
    //            }
    //
    //            for ( String user : users ) {
    //                trie.subCount(user);
    //                Integer temp = travelNode(trie.root);
    //                min = (temp > min ? min : temp);
    //                trie.addCount(user);
    //            }
    //            System.out.println(min);
    //        }
    //    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;
            current.cnt++;
            for (int i = 0; i < word.length(); i++) {
                TrieNode parent = current;
                current = parent.getChildren().get(word.charAt(i));
                if (current == null) {
                    current = new TrieNode(parent);
                    parent.getChildren().put(word.charAt(i), current);
                } else {
                    current.cnt++;
                }
            }
        }

        public void subCount(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                current = current.getChildren().get(word.charAt(i));
                if (current == null) {
                    return;
                } else {
                    current.subCount();
                }
            }
        }

        public void addCount(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                current = current.getChildren().get(word.charAt(i));
                if (current == null) {
                    return;
                } else {
                    current.addCount();
                }
            }
        }
    }
}
