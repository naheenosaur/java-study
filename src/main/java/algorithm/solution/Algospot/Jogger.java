package algorithm.solution.Algospot;
/*
문제
Every morning, Joe the Jogger goes for a brisk run around his neighborhood.
The houses in the neighborhood (which are numbered from 1 to n) are connected together by a set of roads with the property that between every two houses,
there exists exactly one unique path.
That is, the graph structure of the road network is a tree of unknown topology,
consisting of n leaf nodes (corresponding to each of the houses)
and up to n−1 (but possibly fewer) internal nodes denoting intersections where roads split or merge (see Figure 4).

In this problem, your task is to help Joe plan a jogging route from one house in the neighborhood to another house.
Because Joe is a veteran jogger, he wants his route to take as long as possible.
Given a matrix of distances (in meters) between each pair of houses along the road graph,
the number of seconds r it takes Joe to run one meter,
and the number of seconds t it takes for Joe to cross each intersection along the route,
determine the pair of houses for which the total travel time is as long as possible.

Figure 4: Diagram of neighborhood with n = 9.
Houses correspond to numbered nodes, whereas internal nodes of the tree (i.e., intersections) correspond to filled nodes. N
ote that multiple roads may meet at a single intersection.
Here, d3,9 = 9+1+7+6 = 23. If r = 1 and t = 5, then running from house 3 to house 9 takes Joe 1 × 23 + 3 × 5 = 38 seconds.
In the neighborhood shown above, this is the longest possible route, timewise, that Joe can take.

입력
The input test file will contain multiple cases.
Each test case begins with a line containing three integers: n (where 1 ≤ n ≤ 50), the total number of houses in the neighborhood,
r (where 1 ≤ r ≤ 10), the number of seconds per meter traveled, and t (where 1 ≤ t ≤ 100), the number of seconds needed to cross each intersection.
Then, the next n lines each contain n values indicating the distances dij (where 1 ≤ dij ≤ 1000) between each pair of houses.
The distances are specified in row-major order; i.e., the jth entry of the ith line is dij .

You are guaranteed that dii = 0 for each i and that dij = dji for i 6= j. Furthermore, you may as-
sume that the distance matrix corresponds to path lengths along some valid tree,
no house coincides with any internal node of the tree, each internal node of the tree has degree ≥ 3, and all edges in the tree have positive length.
Input is terminated by a single line containing the number 0; do not process this line.

출력
For each test case, you must print a single line of output containing the longest total travel time possible.

예제 입력
9 1 5
0 8 22 16 16 13 24 14 11
8 0 20 14 14 11 22 12 9
22 20 0 12 12 11 22 12 23
16 14 12 0 4 5 16 6 17
16 14 12 4 0 5 16 6 17
13 11 11 5 5 0 13 3 14
24 22 22 16 16 13 0 14 25
14 12 12 6 6 3 14 0 15
11 9 23 17 17 14 25 15 0
0
예제 출력
38
 */


/*
-
5 + 3
5 + 7 + 1 + 9
5 + 7 + 1 + 1 + 2
5 + 7 + 1 + 1 + 2
5 + 7 + 1
5 + 7 + 12
5 + 7 + 2
5 + 6

3 + 5
-
3 + 7 + 1 + 9
3 + 7 + 1 + 1 + 2
3 + 7 + 1 + 1 + 2
3 + 7 + 1
3 + 7 + 12
3 + 7 + 2
3 + 6

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.StringTokenizer;

public class Jogger {
    private static Integer House, speed, rest;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String testCase = bufferedReader.readLine();
            if ("0".equals(testCase)) {
                return;
            }
            StringTokenizer st = new StringTokenizer(testCase);
            House = Integer.parseInt(st.nextToken());
            speed = Integer.parseInt(st.nextToken());
            rest = Integer.parseInt(st.nextToken());
            List<List<Integer>> inputData = new ArrayList<>();
            for (int i = 0; i < House; i++) {
                st = new StringTokenizer(bufferedReader.readLine());
                List<Integer> temp = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    temp.add(Integer.parseInt(st.nextToken()));
                }
                inputData.add(temp);
            }
            getRest(inputData);
        }
    }

    public static void getRest(List<List<Integer>> inputData) {
        Integer max = -1;
        for (int a1 = 0; a1 < House; a1++) {
            for (int a2 = 0; a2 < House; a2++) {
                BitSet restSet = new BitSet();
                for (int other = 0; other < House; other++) {
                    restSet.set((inputData.get(a1).get(a2) + inputData.get(a1).get(other) - inputData.get(a2).get(other)) / 2);
                }
                Integer time = (inputData.get(a1).get(a2) * speed) + ((restSet.cardinality() - 2) * rest);
                if (time > max) {
                    max = time;
                }
            }
        }
        System.out.println(max);
    }
}

//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class JOGGER {
//    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//    public static StringTokenizer st;
//    public static List<List<Integer>> inputData = new ArrayList<>();
//    public static List<Set<Integer>> nodeGroupList = new ArrayList<>();
//    public static List<List<Integer>> interSectionLength = new ArrayList<>();
//    public static List<List<Integer>> interSection = new ArrayList<>();
//    public static Map<Integer, Integer> nodeLength = new HashMap<>(); // <House, length>
//    public static Integer House, speed, rest;
//    public static int MAX = 987654321;
//
//
//    public static void main(String[] args) throws IOException {
//        st = new StringTokenizer(bufferedReader.readLine());
//        House = Integer.parseInt(st.nextToken());
//        speed = Integer.parseInt(st.nextToken());
//        rest = Integer.parseInt(st.nextToken());
//        for ( int h = 0; h  < House ; h ++) {
//            st = new StringTokenizer(bufferedReader.readLine());
//            List<Integer> temp = new ArrayList<>();
//            while (st.hasMoreTokens()) {
//                temp.add(Integer.parseInt(st.nextToken()));
//            }
//            inputData.add(temp);
//        }
//        st = new StringTokenizer(bufferedReader.readLine());
//        if ("0".equals(st.nextToken())) {
//            getMaxRoute();
//        }
//
//
//    }
//
//    private static void getMaxRoute() {
//        setSetList();
//        getNodeLength();
//        getIntersectionLength();
////        getIntersection();
//    }
//
//    private static void getIntersection() {
//        for ( int i = 0 ; i < interSectionLength.size() ; i ++) {
//            int min = MAX;
//            for ( int j = 0; j < interSectionLength.get(i).size() ; j ++) {
//                if (i == j) {
//                    continue;
//                }
//                if (min > interSectionLength.get(i).get(j)) {
//                    min = interSectionLength.get(i).get(j);
//                }
//            }
//        }
//    }
//
//    private static void getIntersectionLength() {
//        for ( int i = 0; i < nodeGroupList.size() ; i ++) {
//            int startIndex = new ArrayList<>(nodeGroupList.get(i)).get(0);
//            List<Integer> list = new ArrayList<>();
//            for ( int j = 0; j < nodeGroupList.size() ; j ++) {
//                if (i == j) {
//                    list.add(0);
//                    continue;
//                }
//                int descIndex = new ArrayList<>(nodeGroupList.get(j)).get(0);
//                list.add(inputData.get(startIndex).get(descIndex) - nodeLength.get(startIndex) - nodeLength.get(descIndex));
//            }
//            interSectionLength.add(list);
//        }
//    }
//
//
//    private static void getNodeLength() { // 가장 가까운 intersection에서 house까지 거리를 계산함
//        for (int s = 0; s < nodeGroupList.size() ; s ++) {
//            List<Integer> temp = new ArrayList<>(nodeGroupList.get(s));
//            for(int i = 1; i < nodeGroupList.get(s).size(); i ++) {
//                int length = inputData.get(temp.get(0)).get(temp.get(i));
//                int alpha;
//
//                for (int j = 0; j < nodeGroupList.size() ; j ++) {
//                    if (j != temp.get(0) && j != temp.get(i)) {
//                        alpha = inputData.get(j).get(temp.get(0)) - inputData.get(j).get(temp.get(i));
//                        nodeLength.putIfAbsent(temp.get(0), (length + alpha) / 2);
//                        nodeLength.putIfAbsent(temp.get(i), (length - alpha) / 2);
//                        break;
//                    }
//                }
//            }
//        }
//
//        for (int i = 0; i < House; i++) { // 그룹으로 묶여있지 않으면, node간 거리 계산
//            if ( ! nodeLength.containsKey(i)) {
//                int temp1 = 0 ,tempIndex1 = 0, temp2 = 0, tempIndex2= 0;
//                for (int s = 0; s < nodeGroupList.size(); s++) {
//                    if (nodeGroupList.get(s).contains(i) || nodeGroupList.get(s).size() == 1) {
//                        continue;
//                    }
//                    if (tempIndex1 == 0) {
//                        tempIndex1 = new ArrayList<>(nodeGroupList.get(s)).get(0);
//                        temp1 = inputData.get(i).get(tempIndex1) - nodeLength.get(tempIndex1);
//                    } else {
//                        tempIndex2= new ArrayList<>(nodeGroupList.get(s)).get(0);
//                        temp2 = inputData.get(i).get(tempIndex2) - nodeLength.get(tempIndex2);
//                        int length = (temp1 + temp2 - inputData.get(tempIndex1).get(tempIndex2)) / 2;
//                        if ( length != temp2) {
//                            nodeLength.putIfAbsent(i, (temp1 + temp2 - inputData.get(tempIndex1).get(tempIndex2)) / 2);
//                            break;
//                        }
//                    }
//                }
//                temp1 = inputData.get(i).get(tempIndex1);
//                temp2 = inputData.get(i).get(tempIndex2);
//                nodeLength.putIfAbsent(i, (temp1 + temp2 - inputData.get(tempIndex1).get(tempIndex2)) / 2);
//            }
//        }
//
//    }
//
//    private static void setSetList() { // 같은 intersection을 지나는 집들 묶어줌
//        for ( int i = 0; i < inputData.size() ; i ++) {
//            for ( int j = i ; j < inputData.size() ; j ++) {
//                int diff = MAX;
//                for ( int index = 0; index < inputData.size() ; index ++) {
//                    if (index == i || index == j) {
//                        if (index == j && index == inputData.size() - 1) {
//                            addSet(i, j);
//                        } else {
//                            continue;
//                        }
//                    }
//
//                    if (diff == MAX) {
//                        diff = inputData.get(j).get(index) - inputData.get(i).get(index);
//                        continue;
//                    }
//                    if (inputData.get(j).get(index) - inputData.get(i).get(index) != diff) {
//                        break;
//                    }
//
//                    if (index == inputData.size() - 1) {
//                        addSet(i, j);
//                    }
//                }
//            }
//        }
//    }
//
//    public static void addSet(int i, int j) {
//        if (nodeGroupList.size() == 0) {
//            Set<Integer> set = new HashSet<>();
//            set.add(i);
//            set.add(j);
//            nodeGroupList.add(set);
//        } else {
//            for (int s = 0; s < nodeGroupList.size(); s++) {
//                if (nodeGroupList.get(s).contains(i)) {
//                    nodeGroupList.get(s).add(j);
//                    break;
//                }
//                if (s == nodeGroupList.size() - 1) {
//                    Set<Integer> set = new HashSet<>();
//                    set.add(i);
//                    set.add(j);
//                    nodeGroupList.add(set);
//                }
//            }
//        }
//    }
//
//
//}
