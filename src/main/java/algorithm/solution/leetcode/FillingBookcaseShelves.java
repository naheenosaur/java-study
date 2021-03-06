package algorithm.solution.leetcode;

/*
We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].

We want to place these books in order onto bookcase shelves that have total width shelf_width.

We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.  We repeat this process until there are no more books to place.

Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.

Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.



Example 1:


Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.


Constraints:

1 <= books.length <= 1000
1 <= books[i][0] <= shelf_width <= 1000
1 <= books[i][1] <= 1000

Output: 6

 */
public class FillingBookcaseShelves {
    public static int minHeightShelves(int[][] books, int shelf_width) {

        int[] sumOfShelvesHeight = new int[books.length + 1];

        sumOfShelvesHeight[0] = 0;

        for (int bookIndex = 0; bookIndex < books.length; bookIndex++) {
            // put books[bookIndex] on new shelf
            sumOfShelvesHeight[bookIndex + 1] = sumOfShelvesHeight[bookIndex] + books[bookIndex][1];

            int height = books[bookIndex][1];
            int width = books[bookIndex][0];
            for (int bringToSameShelfBookIdex = bookIndex - 1; bringToSameShelfBookIdex >= 0; bringToSameShelfBookIdex--) {
                height = Math.max(height, books[bringToSameShelfBookIdex][1]);
                width += books[bringToSameShelfBookIdex][0];
                if (width > shelf_width) {
                    break;
                }
                sumOfShelvesHeight[bookIndex + 1] = Math.min(sumOfShelvesHeight[bookIndex + 1], (sumOfShelvesHeight[bringToSameShelfBookIdex] + height));
            }
        }
        return sumOfShelvesHeight[books.length];
    }

    public static void main(String[] args) {
        int[][] books = {{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
        System.out.println(minHeightShelves(books, 4));
    }
}
