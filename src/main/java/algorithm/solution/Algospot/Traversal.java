package algorithm.solution.Algospot;
/*
트리를 순회하는 알고리즘은 트리의 모든 노드들을 특정 순서에 맞춰 방문하지만,
트리는 배열처럼 1차원적인 구조가 아니기 때문에 단 한 가지의 당연한 순서가 존재하지 않습니다.
때문에 필요에 맞춰 순서를 정의해야 합니다.
이진 트리(binary tree)는 모든 노드에 왼쪽과 오른쪽, 최대 두 개의 자손이 있는 트리를 말하는데,
이진 트리의 순회 순서 중 유명한 세 가지로 전위 순회(preorder traverse), 중위 순회(inorder traverse) 그리고 후위 순회(postorder traverse)가 있습니다.
들은 모두 왼쪽 서브트리를 오른쪽보다 먼저 방문한다는 점에선 같지만, 트리의 루트를 언제 방문하는지가 서로 다릅니다.

전위 순회는 맨 처음에 트리의 루트를 방문하고, 왼쪽과 오른쪽 서브트리를 순서대로 방문합니다.
중위 순회는 왼쪽과 오른쪽 서브트리 사이에 트리의 루트를 방문하고, 후위 순회는 왼쪽과 오른쪽 서브트리를 모두 방문한 뒤에야 루트를 방문합니다.

다음 그림은 이진 트리의 한 예를 보여 줍니다. 이 트리를 전위 순회하면 모든 노드를 27, 16, 9, 12, 54, 36, 72의 순서대로 방문하게 됩니다.
반면 중위 순회했을 때는 9, 12, 16, 27, 36, 54, 72의 순서로, 후위 순회했을 때는 12, 9, 16, 36, 72, 54, 27의 순서로 방문하게 되지요.

어떤 이진 트리를 전위 순회했을 때 노드들의 방문 순서와, 중위 순회했을 때 노드들의 방문 순서가 주어졌다고 합시다.
이 트리를 후위 순회했을 때 각 노드들을 어떤 순서대로 방문하게 될지 계산하는 프로그램을 작성하세요.

입력
입력의 첫 줄에는 테스트 케이스의 수 C (1≤C≤100)가 주어집니다.
각 테스트 케이스는 세 줄로 구성되며, 첫 줄에는 트리에 포함된 노드의 수 N (1≤N≤100)이 주어집니다.
그 후 두 줄에 각각 트리를 전위 순회했을 때와 중위순회 했을 때의 노드 방문 순서가 N개의 정수로 주어집니다.
각 노드는 1000 이하의 자연수로 번호 매겨져 있으며, 한 트리에서 두 노드의 번호가 같은 일은 없습니다.

출력
각 테스트 케이스마다, 한 줄에 해당 트리의 후위 순회했을 때 노드들의 방문 순서를 출력합니다.

예제 입력
2
7
27 16 9 12 54 36 72
9 12 16 27 36 54 72
6
409 479 10 838 150 441
409 10 479 150 838 441

예제 출력
12 9 16 36 72 54 27
10 150 441 838 479 409
 */

/*
27, 16, 9, 12, 54, 36, 72 - root, left, right
9, 12, 16, 27, 36, 54, 72 - left, root, right
- 최상단 root = 27
- root의 왼쪽 = 9, 12, 16
    - 16, 9, 12 - root, left, right
    - 9, 12, 16 - left, root, right
    - 최상단 root = 16
    - root의 왼쪽 = 9, 12
    - root의 오른쪽 = X
        - 9, 12 - root, left, right
        - 9, 12 - left, root, right
        - 최상단 root = 9
        - root의 왼쪽 = X
        - root의 오른쪽 = 12
- root의 오른쪽 = 36, 54, 72
    - 54, 36, 72 - root, left, right
    - 36, 54, 72 - left, root, right
    - 최상단 root = 54
    - root의 왼쪽 = 36
    - root의 오른쪽 = 72

        27
    16      54
 9        36  72
  12


 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Traversal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCase; i++) {
            List<Integer> preOrderList = new ArrayList<>();
            List<Integer> inOrderList = new ArrayList<>();
            int nodes = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                preOrderList.add(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                inOrderList.add(Integer.parseInt(st.nextToken()));
            }
            for (int node : get(preOrderList, inOrderList, nodes)) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

    private static List<Integer> get(List<Integer> preOrderList, List<Integer> inOrderList, int treeSize) {
        if (preOrderList.size() == 0) {
            return new ArrayList<>();
        }
        List<Integer> postOrderList = new ArrayList<>();
        int root = (preOrderList.get(0));
        int nodeIndex = inOrderList.indexOf(root);
        postOrderList.addAll(get(preOrderList.subList(1, nodeIndex + 1), inOrderList.subList(0, nodeIndex), nodeIndex));
        postOrderList.addAll(get(preOrderList.subList(nodeIndex + 1, treeSize), inOrderList.subList(nodeIndex + 1, treeSize), treeSize - nodeIndex - 1));
        postOrderList.add(root);
        return postOrderList;
    }
}
